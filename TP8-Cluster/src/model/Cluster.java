package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import ast.util.Utils;
import refactors.CreatePackage;

public class Cluster {
	
	private ArrayList<Similaridade> similaridadeCluster;
	private ArrayList<Entry> filaDeSimilaridades;
	private Set<String> classesClusters;
	private Map<String, Set<String>>dependencias; 
	private Map<String, Set<String>>dependenciasCluster; 
	private boolean primeiroCluster = true;
	
	public Cluster() {
		this.classesClusters = new HashSet<>();
		this.dependenciasCluster = new HashMap<>();
		filaDeSimilaridades = new ArrayList<Entry>();
	}

	public boolean criarPrimeiroCluster( Set<String> classesProjeto, Map<String, Set<String>>dependencias) throws Exception {
		
		
		for (String classe1 : classesProjeto) {
			for (String classe2 : classesProjeto) {
				analyse(classe1, classe2, dependencias );
			}
		}
		String oi;
		Entry maiorSimilaridade = filaDePrioridadeSimilaridades(filaDeSimilaridades);
		System.out.println("Maior similaridade: " + maiorSimilaridade.getDadosClasses().getNameClass1());
		String c1 = maiorSimilaridade.getDadosClasses().getNameClass1();
		String c2 = maiorSimilaridade.getDadosClasses().getNameClass2();
		this.classesClusters.add(c1);
		this.classesClusters.add(c2);
		
		Set<String> depsCluster = getDependenciesForCluster(c1, c2, dependencias);
		this.dependenciasCluster.put(c1 + ";" + c2, depsCluster);
		System.out.println("Deps clusters ");
		Utils.getInstance().imprime(depsCluster);		
		filaDeSimilaridades.clear();
		return true;
	}
	
	public ArrayList<Similaridade> getSimilarities() {
		return similaridadeCluster;
	}

	public void setSimilarities(ArrayList<Similaridade> similarities) {
		this.similaridadeCluster = similarities;
	}

	public Set<String> getClassesClusters() {
		return classesClusters;
	}

	public void setClassesClusters(Set<String> classesClusters) {
		this.classesClusters = classesClusters;
	}

	public Map<String, Set<String>> getDependenciasCluster() {
		return dependenciasCluster;
	}

	public void setDependenciasCluster(Map<String, Set<String>> dependenciasCluster) {
		this.dependenciasCluster = dependenciasCluster;
	}

	public void analyse(String c1, String c2, Map<String, Set<String>>dependencias) throws Exception {
		int cont = 0;
		float totalSimilaridade = 0;
		ArrayList<String> analysedClass = new ArrayList<String>();
	//	filaDeSimilaridades = new ArrayList<Entry>();
		String classesSeraoAnalisadas = c1 + ";" + c2;
		String inverte = c2 + ";" + c1;
		if (c1.equals(c2)) {
			analysedClass.add(inverte);
		} else {
			if (!verifyAnalisedClasses(analysedClass, classesSeraoAnalisadas)) { 
				// verifica se as classes X e Y já foram analisadas (Y e X)

				// System.out.println("Classe: " + c1 + " com a classe " + c2);
				Dependency.getInstance().verifyDependenciesOfSuperClass(c1, c2, dependencias);
				//Utils.getInstance().imprime3(dependencias);
				totalSimilaridade = verifySimilarity(c1, c2, dependencias);
				cont++;
				analysedClass.add(inverte);
				Similaridade novaSimilaridade = new Similaridade(c1,c2,totalSimilaridade);
			//	similaridadeCluster.add(novaSimilaridade);
				filaDeSimilaridades.add(new Entry(novaSimilaridade));

				//entradas.add(new Entry(classesSeraoAnalisadas, totalSimilaridade));

			}
		}
	}
	private boolean verifyAnalisedClasses(ArrayList<String> analyse, String c) {
		for (String s : analyse) {
			if (s.equals(c))
				return true;
		}
		return false;
	}
	private float verifySimilarity(String c1, String c2, Map<String, Set<String>> dependencias) {
		Set<String> deps1 = new HashSet<>();
		Set<String> deps2 = new HashSet<>();
		float a = 0;
		float similaridade = 0;
		float peso = 0;
		float b = 0;
		float c = 0;

		deps1.addAll(dependencias.get(c1));
		deps2.addAll(dependencias.get(c2));

		int t1 = deps1.size();
		int t2 = deps2.size();
		for (String s1 : deps1) {
			for (String s2 : deps2) {
				if (s1.equals(s2)) {
					a += 1;
				}
				String array1[] = new String[2];
				array1 = c1.split("[.]");

				// System.out.println("veio alto aqui?? " + c1 + " and " + c2);
				String array2[] = new String[2];
				array2 = c2.split("[.]");

				String newClass1 = array1[0];
				String newClass2 = array2[0];

				if (deps1.contains(newClass2) || deps2.contains(newClass1)) {
					peso = (float) 1.2;
				}
			}
		}

		b = (float) t1 - a;
		c = (float) t2 - a;
		if (!(peso == 0.0)) {
			similaridade = (a / (b + c + a)) * peso;
		} else {
			similaridade = (a / (b + c + a));
		}

		peso = 0;
		return similaridade;

	}
	private Entry filaDePrioridadeSimilaridades(ArrayList<Entry> fila) {

		PriorityQueue<Entry> queue = new PriorityQueue<>();
		queue.addAll(fila);
		Entry maiorPrioridade = null;
		if (!queue.isEmpty()) {
			maiorPrioridade = queue.remove();
		}
		return maiorPrioridade;
	}
	
	public Set<String> getDependenciesForCluster(String class1, String class2, Map<String, Set<String>> dependencias) {
		Set<String> aux1 = dependencias.get(class1);
		Set<String> aux2 = dependencias.get(class2);
		Set<String> depCluster  = new HashSet<String>();
		depCluster.addAll(aux1);
		depCluster.addAll(aux2);
		return depCluster;
	}
}
