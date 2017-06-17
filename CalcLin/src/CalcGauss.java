import Jama.Matrix;


public class CalcGauss extends CalcMor {
	

public void escalona(double[][] matriz) {  // Escalona a Matriz
        int ordem = matriz.length;  
        for ( int i=0; i < ordem; i++) {  
            double pivo = matriz[i][i];    
            for (int c=i; c < (ordem+1); c++){ 
            	if (pivo==0){
            		matriz[i][c]=0;
            	}
            	else matriz[i][c] /= pivo; 
            }
            for ( int l=i+1; l < ordem; l++) {  
                double x = matriz[l][i] * -1;  
                for ( int c=i; c < (ordem+1); c++)  
                    matriz[l][c] += matriz[i][c] * x;  
            }  
        }  
          
    }
	
public void solucionar(int OrdemMatriz, double[][] MatrizAumentada){
		
		double [][] matriz = new double [OrdemMatriz][OrdemMatriz]; // Matriz dos coeficientes
		double[] x = new double[OrdemMatriz];				// Matriz do resultado
		double[] b = new double[OrdemMatriz];				// Matriz dos termos independentes
		
		// Usando aqui os recursos da bliblioteca JAMA
		Matrix A = new Matrix(MatrizAumentada);					//Criando matriz
		Matrix MatrizCoeficientes = A.getMatrix(0, (OrdemMatriz-1), 0, (OrdemMatriz-1));
		Matrix B = A.getMatrix(0, (OrdemMatriz-1), (OrdemMatriz), (OrdemMatriz));
        
		for (int i=0; i<OrdemMatriz; i++){ // Atribuindo os valores de b[]
        	b[i] = B.get(i, 0); 
        }
        
		for (int i=0; i<OrdemMatriz; i++){				// Atribuindo os valores de matriz[][]
        	for (int j=0; j<OrdemMatriz; j++){
        		matriz[i][j] = MatrizCoeficientes.get(i, j);
        	}
        }
        
		for (int i = OrdemMatriz - 1; i >= 0; i--) {		// Fazendo back substitution aqui
            double sum = 0.0;
            for (int j = i + 1; j < OrdemMatriz; j++) {
                sum += MatrizCoeficientes.get(i,j) * x[j];
            }
            x[i] = (b[i] - sum) / matriz[i][i];
        }
        for (int i=0; i<OrdemMatriz; i++){
        	System.out.println("X"+i+" = " +x[i]);
        }
		
	}
	
	public void checarSist(int OrdemMatriz, double[][] MatrizAumentada){
		 
		
		double [][] matriz = new double [OrdemMatriz][OrdemMatriz];
		double[] x = new double[OrdemMatriz];
		double[] b = new double[OrdemMatriz];
		Matrix C = new Matrix(matriz);
		Matrix A = new Matrix(MatrizAumentada);
		Matrix MatrizCoeficientes = A.getMatrix(0, (OrdemMatriz-1), 0, (OrdemMatriz-1));
		Matrix B = A.getMatrix(0, (OrdemMatriz-1), (OrdemMatriz), (OrdemMatriz));
        for (int i=0; i<OrdemMatriz; i++){
        	b[i] = B.get(i, 0); 
        }   
        for (int i=0; i<OrdemMatriz; i++){
        	for (int j=0; j<OrdemMatriz; j++){
        		matriz[i][j] = MatrizCoeficientes.get(i, j);
        	}
        }
		        // Analisando as condi��es dos sistemas
		        if (matriz[OrdemMatriz-1][OrdemMatriz-1]==0&&b[OrdemMatriz-1]==0){
		        	System.out.println("Possui Infinitas solu��es");
		        }
		        if (matriz[OrdemMatriz-1][OrdemMatriz-1]!=0&&b[OrdemMatriz-1]!=0){
		        	System.out.println("Possui uma solu��o");
		        }
		        if (matriz[OrdemMatriz-1][OrdemMatriz-1]==0&&b[OrdemMatriz-1]!=0){
		        	System.out.println("Sistema Imposs�vel");
		        }
		
			 
	}
	public void CalculaTudo(int OrdemMatriz, double[][] MatrizAumentada){ //M�todo que vai expor todos resultados pretendidos
		CalcGauss a = new CalcGauss();
		a.escalona(MatrizAumentada);
		a.checarSist(OrdemMatriz, MatrizAumentada);
		a.solucionar(OrdemMatriz, MatrizAumentada);
		a.calres�duo(MatrizAumentada, OrdemMatriz);
	}

	@Override
	public void calres�duo(double[][] MatrizAumentada, int OrdemMatriz) { // m�todo para calcular o erro residual
		
		Matrix A = new Matrix(MatrizAumentada);
	    Matrix MatrizCoeficientes = A.getMatrix(0, (OrdemMatriz-1), 0, (OrdemMatriz-1));
	    Matrix B = A.getMatrix(0, (OrdemMatriz-1), (OrdemMatriz), (OrdemMatriz));
	    Matrix C = MatrizCoeficientes.solve(B);
	    
		Matrix residual = MatrizCoeficientes.times(C).minus(B);
	      double rnorm = residual.normInf();
	      System.out.println("Erro residual = "+rnorm);
	}
}
