import java.util.Scanner;

public class ExecutoraMor {
	
	public static void main (String[] args){
		
		int OrdemMatriz = 1;
		
		Scanner ler = new Scanner(System.in); //Criando ler do teclado
	    
		System.out.println("Digite o n�mero de equa��es do sistema");
					 
		try{
			OrdemMatriz = ler.nextInt(); //Guarda a Ordem da Matriz que deve ser igual ao n�mero de equa��es do sistema
		}		
		catch (Exception e){ 
			System.out.println("erro: "+ e);
			System.out.println("Esse n�o � um n�mero v�lido para quantidade de equa��es");
			System.exit(0);
		}
			
		try{
			double[][] MatrizAumentada = new double[OrdemMatriz][OrdemMatriz+1];
		}
		catch (java.lang.NegativeArraySizeException e){
			System.out.println("erro: " +e);
			System.out.println("Esse n�o � um n�mero de equa��es v�lido");
			System.exit(0);
		}
		
		double[][] MatrizAumentada = new double[OrdemMatriz][OrdemMatriz+1]; //Atribui o tamanho da MatrizAumentada
		
	    for (int i= 0; i<OrdemMatriz; i++){								//Preenche a MatrizAumentada pelo teclado
	    	System.out.println("Digite os coeficientes da "  +(i+1)+ "� equa��o");
	    	for (int j=0; j<(OrdemMatriz+1); j++){
	    		MatrizAumentada[i][j] = ler.nextDouble(); 
	    	}
	    }
	    	    
	    System.out.println("Deseja resolver por: 1. Cramer        2.Gauss		3.GJordan	4.Inversa"); // Lista para escolha da calculadora espec�fica
		
	    int metodo = ler.nextInt();	// Decide o m�todo de resolu��o que vai usar
		
		final int Cramer = 1;
		final int Gauss = 2;
		final int GJordan = 3;
		final int Inversa = 4;
		final int Cholesky = 5;
		final int Jacobi = 6;
		
		switch (metodo){
		
		case (Cramer):{
			CalCramer CramerSolve = new CalCramer(MatrizAumentada, OrdemMatriz);
			CramerSolve.CalculaTudo(OrdemMatriz, MatrizAumentada);
			break;}	
		
		case (Gauss):{
			CalcGauss GaussSolve = new CalcGauss();
			try{
			GaussSolve.CalculaTudo(OrdemMatriz, MatrizAumentada);
			}
			catch (java.lang.RuntimeException e){
				System.out.println("erro: " + e);
				
				}
			break;
			}
		
		case (GJordan):{
			CalcGJordan GJordanSolve = new CalcGJordan();
			try{
			GJordanSolve.CalculaTudo(OrdemMatriz, MatrizAumentada);
			}
			catch (java.lang.RuntimeException e){
				System.out.println("erro: " + e);
				
				}
			break;
		}	
		
		case (Inversa): {
			CalcInversa InversaSolve = new CalcInversa();
			try{
				InversaSolve.CalculaTudo(OrdemMatriz, MatrizAumentada);
			}
			catch (java.lang.RuntimeException e){
				System.out.println("erro: " + e);
				
				}
			break;
			}
			
		default: System.out.println("Ainda n�o sei resolver por outros m�todos");
			break;
		
    }
}
}
