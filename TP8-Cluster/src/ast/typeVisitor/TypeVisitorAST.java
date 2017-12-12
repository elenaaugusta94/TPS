package ast.typeVisitor;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class TypeVisitorAST extends ASTVisitor {

	private Set<MethodDeclaration> methods ;
	private Set<TypeDeclaration> declarations;
//	private ICompilationUnit unit; 
	private CompilationUnit compUnit;
//	private String className;
	
	public TypeVisitorAST(ICompilationUnit unit) throws JavaModelException {
		
	//	Document document = new Document(unit.getSource());
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		compUnit = (CompilationUnit) parser.createAST(null);
		// início do registro das modificações
		compUnit.recordModifications();
	//	AST ast = compUnit.getAST();
		this.declarations = new LinkedHashSet<TypeDeclaration>();		
		this.methods = new LinkedHashSet<MethodDeclaration>();
		
		
	}
	
	public boolean visit(TypeDeclaration node) {
		declarations.add(node);
		return super.visit(node);
	}
	public boolean visit(MethodDeclaration node) {
		methods.add(node);
		return super.visit(node);
	}
	public Set<TypeDeclaration> getDeclaration() {
		return declarations;
	}
	public Set<MethodDeclaration> getMethods(){
		return methods;
	}

	public CompilationUnit getCompUnit() {
		return compUnit;
	}

	
	

	
}
