package br.com.pontoclass.fiap.plugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import br.com.pontoclass.exercise.Exercise;
import br.com.pontoclass.reflection.ReflectionHelper;

/** 
 * This goal will process a ... 
 * 
 * @goal exercicios 
 * @phase compile 
 * @author Raiane Jessica
**/
@Mojo(name="exercicios", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class FiapExercisesMojo extends AbstractMojo {

	public void execute() throws MojoExecutionException, MojoFailureException {
		new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("begin.template")))
			.lines()
			.forEach((line) -> getLog().info(line));
		final List<Class<? extends Exercise>> classes = new ArrayList<>();
		Arrays.asList(Package.getPackages())
		.parallelStream()
		.forEach((pack)->classes.addAll(ReflectionHelper.findClassesImplementing(Exercise.class, pack)));
		try {
			Predicate<Exercise> exercisePredicate = (clazz)->clazz.getConstructor().newInstance();
			List<? extends Exercise> exercises = classes.parallelStream().collect(Collectors.mapping(exercisePredicate, Collectors.toList()));
			exercises.forEach((exercise)->{
				  getLog().info(exercise.toString());
			  });
			Arrays.asList(Exercise.class.getClasses())
				  .stream().forEach((clazz)-> {
					  getLog().info(clazz.getName());
				  });
		} catch(Exception e) {
			
		}
		
		new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("end.template")))
		.lines()
		.forEach((line) -> getLog().info(line));
	}
}