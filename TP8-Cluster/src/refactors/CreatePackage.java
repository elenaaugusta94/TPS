package refactors;

import javax.swing.JOptionPane;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

public class CreatePackage {
	
	private static int cont = 1;
	private static IPackageFragment pacote = null;
	

	public static void createPackage(IProject project) throws JavaModelException {
		

		if (JOptionPane.showConfirmDialog(null, "Um cluster serah criado para agrupar classes similares, deseja continuar?") == JOptionPane.YES_OPTION) {
			IJavaProject javaProject = JavaCore.create(project);
			IFolder folder = project.getFolder("src");
			IPackageFragmentRoot srcFolder = javaProject.getPackageFragmentRoot(folder);
			IPackageFragment fragment = srcFolder.createPackageFragment("cluster" + cont, true, null);
			System.out.println("fragment:  " + fragment.getElementName());
			cont++;
			pacote = fragment;
		}
		
	}
	public static IPackageFragment getPacote() {
		return pacote;
	}
	
	public static String getPackageName() {
		return pacote.getElementName();
	}

}
