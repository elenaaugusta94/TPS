package ast.util;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

import ast.handlers.WorkspaceInfoHandler;
import model.Similaridade;
import refactors.CreatePackage;
import refactors.MoveClass;

public class Utils {

	public static Utils util;

	protected Utils() {
	}

	public static Utils getInstance() {
		if (util == null) {
			util = new Utils();
		}
		return util;
	}

	public void imprime(Map<String, Set<ICompilationUnit>> tipo) {
		for (String c : tipo.keySet()) {
			if (!tipo.get(c).isEmpty()) {
				System.out.print("[" + c.toString() + "] -->");
				for (ICompilationUnit s : tipo.get(c)) {
					System.out.print(s.getElementName() + "; ");
				}
				System.out.println();
			}
		}
	}

	public void imprime2(Map<String, String> tipo) {
		// System.out.println("---------------------------------");
		// System.out.println("Classe " + "Pacote ");
		for (String c : tipo.keySet()) {
			// if (!tipo.get(c).isEmpty()) {
			System.out.print("[" + c.toString() + "]                         ");
			String s = tipo.get(c);
			System.out.print(s + "; ");

			System.out.println();
			// }
		}
		System.out.println("---------------------------------");
	}
	public void imprime(Set<String> tipo ) {
		for(String t:tipo) {
			System.out.println(" " + t);
		}
	}
	public void imprime3(Map<String, Set<String>> tipo) {
		System.out.println("---------------------------------");
		for (String c : tipo.keySet()) {
			if (!tipo.get(c).isEmpty()) {
				System.out.print("[" + c.toString() + "] ---> ");
				for (String s : tipo.get(c)) {
					System.out.print(s + "; ");
				}
				System.out.println();
			}
		}
		System.out.println("---------------------------------");
	}

	public void imprimeArray(ArrayList<Similaridade> dados) {
		for (Similaridade d : dados) {
				System.out.println(
						d.getNameClass1() + "   " + d.getNameClass1() + "   " + d.getValueSimilarity() + "   ");
		}
	}

	public String[] parserNameClass(String class1, String class2) {
		String array1[] = new String[2];
		array1 = class1.split("[.]");

		String array2[] = new String[2];
		array2 = class2.split("[.]");

		String newClass1 = array1[0];
		String newClass2 = array2[0];

		String[] names = new String[2];
		names[0] = newClass1;
		names[1] = newClass2;
		return names;

	}

	public void messagens(Map<IPackageFragment, Set<ICompilationUnit>> estrutura) throws JavaModelException {
		JDialog.setDefaultLookAndFeelDecorated(true);
		int response = JOptionPane.showConfirmDialog(null, "Voce deseja realizar alguma refatoracao? ", "Confirmacao ",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION) {
			System.out.println("No button clicked");
			//TODO: retirar esse exit
			System.exit(0);
		} else if (response == JOptionPane.YES_OPTION) {
			System.out.println("Yes button clicked");
			changeClass(estrutura);
		} else if (response == JOptionPane.CLOSED_OPTION) {
			System.out.println("JOptionPane closed");
		}
	}

	public void changeClass(Map<IPackageFragment, Set<ICompilationUnit>> estrutura) throws JavaModelException {
		IPackageFragment pc = CreatePackage.getPacote();
		String classe = WorkspaceInfoHandler.getClasses();

		String aux[] = new String[2];
		aux = classe.split(";");

		for (String cl : aux) {
			
			if (pc.getCompilationUnits().length==0) {
						Object[] message = { "Classe a ser Refatorada:", cl, "Pacote de destino: ",
								pc.getElementName() };

						int option = JOptionPane.showConfirmDialog(null, message, "SIM", JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {
							System.out.println("Classe eh: " + cl);
							System.out.println("Pacote eh:  " + pc.getElementName());

							IType t = getClassCompilation(cl, estrutura);
							MoveClass mv = new MoveClass();
							if (mv.performMoveClassRefactoring(t, pc)) {
								System.out.println("Modificacao efetuada com Sucesso!! ");
							} else {
								System.out.println("Erro na Modificacao =/");
							}

						} else {
							System.out.println("Modificacao Cancelada!");
						}
			} else {
				for (IClassFile unit : pc.getClassFiles()) {
					if ((!unit.toString().equals(cl)) && (!cl.contains("cluster"))) {
						Object[] message = { "Classe a ser Refatorada:", cl, "Pacote de destino: ",
								pc.getElementName() };

						int option = JOptionPane.showConfirmDialog(null, message, "SIM", JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {
							System.out.println("Classe eh: " + cl);
							System.out.println("Pacote eh:  " + pc.getElementName());

							IType t = getClassCompilation(cl, estrutura);
							MoveClass mv = new MoveClass();
							if (mv.performMoveClassRefactoring(t, pc)) {
								System.out.println("Modificacao efetuada com Sucesso!! ");
							} else {
								System.out.println("Erro na Modificacao =/");
							}

						} else {
							System.out.println("Modificacao Cancelada!");
						}
					}
				}
			}

		}

	}

	public IPackageFragment packageName(String pacote, Map<IPackageFragment, Set<ICompilationUnit>> estrutura) {
		IPackageFragment p = null;
		for (IPackageFragment Ipackage : estrutura.keySet()) {
			if (Ipackage.getElementName().equals(pacote)) {
				p = Ipackage;
			}
		}
		return p;
	}

	public IType getClassCompilation(String classe, Map<IPackageFragment, Set<ICompilationUnit>> estrutura) {
		IType t = null;
		for (IPackageFragment Ipackage : estrutura.keySet()) {

			for (ICompilationUnit c : estrutura.get(Ipackage)) {
				if (c.getElementName().equals(classe)) {
					t = c.getType(classe);
				}
			}
		}
		return t;
	}

}
