package br.com.pontoclass.fiap.plugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.codehaus.plexus.util.StringUtils;

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

	@SuppressWarnings({"rawtypes", "unchecked"}) public void execute() throws MojoExecutionException, MojoFailureException {
		new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("begin.template")))
			.lines()
			.forEach((line) -> getLog().info(line));
		
		final List<Class<? extends Exercise>> classes = new ArrayList<>();
		Arrays.asList(Package.getPackages())
		.parallelStream()
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
		   .map(constructors)
		   .map(instances)
		   .collect(Collectors.toList());
		for(int i = 0; i < exercises.size(); i++) {
			Exercise exercise = exercises.get(i);
			Stream.of(Stream.of(exercise.getStatement().split("\n"))
							.collect(Collectors.joining("\n\t    ", String.format("\t%d - ", i+1), "\n"))
							.split("\n"))
				  .filter((s)->!s.isEmpty())
				  .forEach((s)->getLog().info(s));
			getLog().info("");
		}
		getLog().info("________________________________________________________________________");
		getLog().info("");
		System.out.print("[INPUT] Opção: ");

		try(final Scanner scan = new Scanner(System.in)) {
			String selected = scan.nextLine();
			
			if(!StringUtils.isNumeric(selected)) {
				getLog().error("#OPÇÃO INVÁLIDA, O PLUGIN SERÁ ENCERRADO");
			}
			if(Integer.valueOf(selected) > exercises.size()) {
				getLog().error("#OPÇÃO INEXISTENTE, O PLUGIN SERÁ ENCERRADO");
			} else {
				Exercise exercise = exercises.get(Integer.valueOf(selected)-1);
				Stream.of(Stream.of(exercise.getStatement().split("\n"))
						.collect(Collectors.joining("\n\t    ", String.format("\t%s - ", selected), "\n"))
						.split("\n"))
						.filter((s)->!s.isEmpty())
						.forEach((s)->getLog().info(s));
				getLog().info("");
				getLog().info("________________________________________________________________________");
				getLog().info("");
				final Map<String, String> inputMap = new HashMap<>();
				exercise.getInputNames()
	                    .stream()
	                    .forEach((desc)->{
	                    	Stream.of(Stream.of(desc.split("\n"))
	            				  .collect(Collectors.joining("\n        ", " VALOR [", "]\n"))
	            				  .split("\n"))
	            				  .filter((s)->!s.isEmpty())
	            				  .forEach((s)->System.out.println(String.format("[INPUT] %s", s)));
	                    	System.out.print("[INPUT]  Digite: ");
	                    	String in = scan.nextLine();
	                    	inputMap.put(desc, in);
	                    });
				exercise.setInputMap(inputMap);
				getLog().info("");
				getLog().info("Iniciando processamento....");
				getLog().info("");
				try {
					exercise.solve();
					Stream.of(Stream.of(exercise.getResultDescription().split("\n"))
	      				  .collect(Collectors.joining("\n ", " SOLUÇÃO: ", "\n"))
	      				  .split("\n"))
	      				  .filter((s)->!s.isEmpty())
	      				  .forEach((s)->System.out.println(String.format("[OUTPUT] %s", s)));
				} catch(Exception e) {
					getLog().error(e.getMessage());
					getLog().info("");
				}
			}
		}
		new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("end.template")))
		.lines()
		.forEach((line)->getLog().info(line));
	}
}