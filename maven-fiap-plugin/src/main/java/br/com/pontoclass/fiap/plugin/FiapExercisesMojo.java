package br.com.pontoclass.fiap.plugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
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
		.peek((pack)->getLog().info(pack.getName()))
		.forEach((pack)->classes.addAll(ReflectionHelper.findClassesImplementing(Exercise.class, pack)));
		Function<Class, Constructor<Exercise>> constructors = (clazz)->{
			try{
				return clazz.getConstructor();
			} catch(Exception e){
				throw new RuntimeException(e);
			}
		};
		Function<Constructor<Exercise>, Exercise> instances = (constructor)->{
			try{
				return Exercise.class.cast(constructor.newInstance());
			} catch(Exception e){
				throw new RuntimeException(e);
			}
		};
		List<? extends Exercise> exercises = classes.parallelStream()
		   .peek((clazz)->getLog().info(clazz.toString()))
		   .map(constructors)
		   .peek((constructor)->getLog().info(constructor.toString()))
		   .map(instances)
		   .peek((exercise)->getLog().info(exercise.toString()))
		   .collect(Collectors.toList());
		exercises.forEach((exercise)->getLog().info(exercise.toString()));
		Arrays.asList(Exercise.class.getClasses()).stream().forEach((clazz)-> getLog().info(clazz.getName()));

		new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("end.template")))
		.lines()
		.forEach((line) -> getLog().info(line));
	}
}