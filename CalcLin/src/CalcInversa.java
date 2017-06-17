import Jama.Matrix;

public class CalcInversa extends CalcMor {
	
	public void solucionar(int OrdemMatriz, double[][] MatrizAumentada){
		
		Matrix A = new Matrix(MatrizAumentada);
		Matrix MatrizCoeficientes = A.getMatrix(0, (OrdemMatriz-1), 0, (OrdemMatriz-1));
	    Matrix A1 = MatrizCoeficientes.inverse();
	    Matrix B = A.getMatrix(0, (OrdemMatriz-1), (OrdemMatriz), (OrdemMatriz));
	    Matrix D = A1.times(B);
	    
	    for (int i = 0; i<OrdemMatriz; i++){
	    	System.out.println("X"+(i+1)+" = "+ D.get(i, 0));
		}
	}
	
	public void checarSist(int OrdemMatriz, double[][] MatrizAumentada){
		
		double [][] matriz = new double [OrdemMatriz][OrdemMatriz];
		double[] b = new double[OrdemMatriz];
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
		        
		        if (matriz[OrdemMatriz-1][OrdemMatriz-1]==0&&b[OrdemMatriz-1]==0){
		        	System.out.println("Possui Infinitas soluções");
		        }
		        if (matriz[OrdemMatriz-1][OrdemMatriz-1]!=0&&b[OrdemMatriz-1]!=0){
		        	System.out.println("Possui uma solução");
		        }
		        if (matriz[OrdemMatriz-1][OrdemMatriz-1]==0&&b[OrdemMatriz-1]!=0){
		        	System.out.println("Sistema Impossível");
		        }
		
			 
	}
	
	public void calresíduo(double [][] MatrizAumentada, int OrdemMatriz) {
		Matrix A = new Matrix(MatrizAumentada);
	    Matrix MatrizCoeficientes = A.getMatrix(0, (OrdemMatriz-1), 0, (OrdemMatriz-1));
	    Matrix A1 = MatrizCoeficientes.inverse();
	    Matrix B = A.getMatrix(0, (OrdemMatriz-1), (OrdemMatriz), (OrdemMatriz));
	    Matrix D = A1.times(B);
		Matrix residual = MatrizCoeficientes.times(D).minus(B); 
		/*double rnorm = residual.normInf();*/
		
		double r = residual.get(0, 0);
		
	      System.out.println("Erro residual = " +r);
	}

	public void CalculaTudo(int OrdemMatriz, double[][] MatrizAumentada){
		CalcInversa a = new CalcInversa();
		a.escalona(MatrizAumentada);
		a.checarSist(OrdemMatriz, MatrizAumentada);
		a.solucionar(OrdemMatriz, MatrizAumentada);
		a.calresíduo(MatrizAumentada, OrdemMatriz);
	}
	
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
}
