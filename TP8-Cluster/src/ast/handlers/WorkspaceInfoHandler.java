package ast.handlers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sound.midi.Soundbank;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import ast.util.Utils;

import model.Dependency;
import model.Entry;
import model.Similaridade;

public class WorkspaceInfoHandler extends AbstractHandler {

	float peso = 0;
	int countMSG = 0;
	public boolean primeiroCluster = true;

	private Set<String> allClass;
	private Set<String> projectClass;
	private Map<IPackageFragment, Set<ICompilationUnit>> estrutura = new HashMap<>();

	// private ArrayList<Cluster> listClusters ;
	private static String classes;

	private int clusterParada;

	public static ArrayList<Similaridade> similaridades;
	public Set<String> allDependencies = new HashSet<>();

	private Map<String, Set<String>> dependencias = new HashMap<>();

	Map<String, Set<ICompilationUnit>> informations = new HashMap<>();

	StringBuilder msg = new StringBuilder();

	ArrayList<Entry> filaDeSimilaridades = new ArrayList<Entry>();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		similaridades = new ArrayList<Similaridade>();
		try {
			// View.getInstance().openView(countMSG);
			getWorkspaceInfo();

		} catch (Exception e) {
			MessageDialog.openInformation(window.getShell(), "ExemploEclipseJDTPlugin",
					"Erro! Não foi possível executar o plug-in!");
			e.printStackTrace();
		}
		MessageDialog.openInformation(window.getShell(), "ExemploEclipseJDTPlugin", msg.toString());

		try {
			Utils.getInstance().messagens(estrutura);
		} catch (JavaModelException e) {
			e.printStackTrace();
		}

		return null;
	}

	// Pega as informações do Workspace
	private void getWorkspaceInfo() throws Exception {

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject[] projects = root.getProjects();
		// Itera sobre todos os projetos
		for (IProject project : projects) {
			if (project.isOpen() && project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
				IJavaProject javaProject = JavaCore.create(project);
				getAllDependenciesOfClass(javaProject);
				criarArquivo();
			}
		}

	}

	private void getAllDependenciesOfClass(IJavaProject javaProject) throws Exception {
		allClass = new HashSet<>();
		projectClass = new HashSet<>();
		Set<ICompilationUnit> classesC = new HashSet<>();

		for (IPackageFragment p : javaProject.getPackageFragments()) {

			if (p.getKind() == IPackageFragmentRoot.K_SOURCE) {
				System.out.println("Pacotes: " + p.getElementName());
				String pacoteAnalisado = p.getElementName();
				// msg.append(String.format("Análise do pacote '%s'\n", pacoteAnalisado));

				ICompilationUnit[] classes = p.getCompilationUnits();
				Set<ICompilationUnit> strSet = Arrays.stream(classes).collect(Collectors.toSet());
				for (ICompilationUnit unit : p.getCompilationUnits()) {

					allClass.add(unit.getElementName());
					classesC.add(unit);
					clusterParada = allClass.size();
					projectClass = allClass;

					Set<String> typeDependencies = Dependency.getInstance().getTypeDepencies(unit);
					this.allDependencies.addAll(typeDependencies);
					dependencias.put(unit.getElementName().toString(), typeDependencies);

				}
				informations.put(pacoteAnalisado, strSet);
				estrutura.put(p, classesC);
			}

		}

	}

	// public void criarClusters() throws Exception {
	// listClusters = new ArrayList<Cluster>();
	// Cluster c = new Cluster();
	// c.criarPrimeiroCluster(allClass,dependencias);
	// listClusters.add(c);
	// imprimeClassesCluster();
	//
	// }
	//
	// public void imprimeClassesCluster() {
	// for(Cluster c : listClusters) {
	// System.out.println(" c: " + c.getClassesClusters());
	// }
	// }

	// public void analyse(String c1, String c2) throws Exception {
	// int cont = 0;
	// float totalSimilaridade = 0;
	// ArrayList<String> analysedClass = new ArrayList<String>();
	//
	// String classesSeraoAnalisadas = c1 + ";" + c2;
	// String inverte = c2 + ";" + c1;
	// if (c1.equals(c2)) {
	// analysedClass.add(inverte);
	// } else {
	// if (!verifyAnalisedClasses(analysedClass, classesSeraoAnalisadas)) {
	// // verifica se as classes X e Y já foram analisadas (Y e X)
	//
	// // System.out.println("Classe: " + c1 + " com a classe " + c2);
	// Dependency.getInstance().verifyDependenciesOfSuperClass(c1, c2,
	// dependencias);
	// Utils.getInstance().imprime3(dependencias);
	// totalSimilaridade = verifySimilarity(c1, c2);
	// cont++;
	// analysedClass.add(inverte);
	// Similaridade novaSimilaridade = new Similaridade(c1,c2,totalSimilaridade);
	// similaridades.add(novaSimilaridade);
	// filaDeSimilaridades.add(new Entry(novaSimilaridade));
	//
	// //entradas.add(new Entry(classesSeraoAnalisadas, totalSimilaridade));
	//
	// }
	// }
	//
	//
	//
	// }

	public Set<String> getAllDependencies() {
		return allDependencies;
	}

	public void setAllDependencies(Set<String> allDependencies) {
		this.allDependencies = allDependencies;
	}

	public void criarArquivo() throws IOException {
		Set<String> dependenciasProjeto = allDependencies;
		
		FileWriter fileWriter = new FileWriter("dependencias.txt");
		BufferedWriter writer = new BufferedWriter(fileWriter);
		for (String s : dependenciasProjeto) {
			System.out.print( "            " + s);
			writer.write(s);
			
		}
		writer.write("\n");
		for (String depP : dependenciasProjeto) {

			for (String c : dependencias.keySet()) {

				if (dependencias.get(c).contains(depP)) {
					System.out.print(c + "      1       " + "                          ");
					writer.write(c + "1");
					// System.out.println();
				} else {
					System.out.print(c+ "      0       " + "                          ");
					writer.write(c + "0");
				}
			}
			System.out.println();
		}
		writer.close();
	}

	

	public void imprimeEssaPorra() {
		for (String d : allDependencies) {

		}
	}

	// private void criaPrimeiroCluster(IProject javaProject) throws Exception {
	// //CreatePackage.createPackage(javaProject);
	// for (String classe1 : allClass) {
	// for (String classe2 : allClass) {
	// analyse(classe1, classe2);
	// }
	// }
	//
	// Entry maiorSimilaridade = filaDePrioridadeSimilaridades(filaDeSimilaridades);
	//
	// // allClass.add(CreatePackage.getPackageName());
	// Set<String> depsCluster =
	// Dependency.getInstance().getDependenciesForCluster(aux[0], aux[1],
	// dependencias);
	// Utils.getInstance().imprime(depsCluster);
	// dependencias.put(CreatePackage.getPackageName(), depsCluster);
	// filaDeSimilaridades.clear();
	//
	// }
	private void clusters(IProject javaProject) throws Exception {
		if (primeiroCluster) {
			// CreatePackage.createPackage(javaProject);
			// for (String classe1 : allClass) {
			// for (String classe2 : allClass) {
			// analyse(classe1, classe2);
			// }
			// }
			// criaPrimeiroCluster(javaProject);
			// Entry element = priorityQueue(entradas);
			// classes = element.getClasses();
			// String[] aux = splitClasses(element.getClasses());
			// for (String cl : aux) {
			// clusterClass1.add(cl);
			// allClass.remove(cl);
			//
			// }
			// allClass.add(CreatePackage.getPackageName());
			// Set<String> depsCluster =
			// Dependency.getInstance().getDependenciesForCluster(aux[0], aux[1],
			// dependencias);
			// Utils.getInstance().imprime(depsCluster);
			// dependencias.put(CreatePackage.getPackageName(), depsCluster);
			// entradas.clear();
			primeiroCluster = false;
			clusterParada--;
		} else {
			//
			// for (String classe1 : allClass) {
			// if (classe1.contains("cluster")) {
			// continue;
			// } else
			// for (String classe2 : allClass) {
			// System.out.println("Clase 1 " + classe1 + " e classe 2 " + classe2);
			// analyse(classe1, classe2);
			//
			// }
			// //TODO: voltar bloco abaixo para cá!!!!!!!!!!
			//
			// }
			// //TODO: Este é o bloco que deve voltar para cima!!!!!!!!!!
			// Entry element = priorityQueue(entradas);
			// classes = element.getClasses();
			// String[] aux = splitClasses(element.getClasses());
			// if (aux[1].contains("cluster1")) {
			// for (String cl : aux) {
			// clusterClass1.add(cl);
			// projectClass.remove(aux[0]);
			// }
			// projectClass.add(CreatePackage.getPackageName());
			// Set<String> depsCluster =
			// Dependency.getInstance().getDependenciesForCluster(aux[0], aux[1],
			// dependencias);
			// Utils.getInstance().imprime(depsCluster);
			// dependencias.put(CreatePackage.getPackageName(), depsCluster);
			// entradas.clear();
			// } else {
			// CreatePackage.createPackage(javaProject);
			// for (String cl : aux) {
			// clusterClass2.add(cl);
			// projectClass.remove(cl);
			//
			// }
			// projectClass.add(CreatePackage.getPackageName());
			// Set<String> depsCluster =
			// Dependency.getInstance().getDependenciesForCluster(aux[0], aux[1],
			// dependencias);
			// Utils.getInstance().imprime(depsCluster);
			// dependencias.put(CreatePackage.getPackageName(), depsCluster);
			// entradas.clear();
			// System.out.println("Tamanhooo " + allClass.size());
			// Utils.getInstance().imprime(allClass);
			// }
			// Utils.getInstance().imprime(allClass);
			// allClass = projectClass;
			// }
			//
			while (clusterParada != 0) {
				Utils.getInstance().messagens(estrutura);
				clusters(javaProject);
				clusterParada--;
			}
		}

	}

	// public String[] splitClasses(String classes) {
	// String aux[] = new String[2];
	// aux = classes.split(";");
	// return aux;
	//
	// }

	// private Entry filaDePrioridadeSimilaridades(ArrayList<Entry> fila) {
	//
	// PriorityQueue<Entry> queue = new PriorityQueue<>();
	// queue.addAll(fila);
	// Entry maiorPrioridade = null;
	// if (!queue.isEmpty()) {
	// maiorPrioridade = queue.remove();
	// }
	// return maiorPrioridade;
	// }
	//
	// private boolean verifyAnalisedClasses(ArrayList<String> analyse, String c) {
	// for (String s : analyse) {
	// if (s.equals(c))
	// return true;
	// }
	// return false;
	// }
	//
	// private float verifySimilarity(String c1, String c2) {
	// Set<String> deps1 = new HashSet<>();
	// Set<String> deps2 = new HashSet<>();
	// float a = 0;
	// float similaridade = 0;
	//
	// float b = 0;
	// float c = 0;
	//
	// deps1.addAll(dependencias.get(c1));
	// deps2.addAll(dependencias.get(c2));
	//
	// int t1 = deps1.size();
	// int t2 = deps2.size();
	// for (String s1 : deps1) {
	// for (String s2 : deps2) {
	// if (s1.equals(s2)) {
	// a += 1;
	// }
	// String array1[] = new String[2];
	// array1 = c1.split("[.]");
	//
	// // System.out.println("veio alto aqui?? " + c1 + " and " + c2);
	// String array2[] = new String[2];
	// array2 = c2.split("[.]");
	//
	// String newClass1 = array1[0];
	// String newClass2 = array2[0];
	//
	// if (deps1.contains(newClass2) || deps2.contains(newClass1)) {
	// peso = (float) 1.2;
	// }
	// }
	// }
	//
	// b = (float) t1 - a;
	// c = (float) t2 - a;
	// if (!(peso == 0.0)) {
	// similaridade = (a / (b + c + a)) * peso;
	// } else {
	// similaridade = (a / (b + c + a));
	// }
	//
	// peso = 0;
	// return similaridade;
	//
	// }

	public ArrayList<Similaridade> getDados() {
		return similaridades;
	}

	public static String getClasses() {
		return classes;
	}

	public Map<String, Set<ICompilationUnit>> getInformations() {
		return informations;
	}
}
