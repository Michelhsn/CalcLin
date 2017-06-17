public class CalCramer extends CalcMor {
	 
	 private double Determinante;
	 private double DeterminanteX;
	 private double DeterminanteY;
	 private double DeterminanteZ;
	
	// TODO Auto-generated constructor stub
	public CalCramer(double[][] MatrizAumentada, int OrdemMatriz){
	
	double [][] MatrizCoeficientes = new double[OrdemMatriz][OrdemMatriz];
	double [][] MatrizX = new double[OrdemMatriz][OrdemMatriz];
	double [][] MatrizY = new double[OrdemMatriz][OrdemMatriz];
	double [][] MatrizZ = new double[OrdemMatriz][OrdemMatriz];
	
	
	for (int i=0;i<OrdemMatriz;i++){
		for (int j=0;j<OrdemMatriz;j++){
			MatrizCoeficientes[i][j] = MatrizAumentada[i][j]; // Criando Matriz dos coeficientes
			}
		}
		
	for (int i=0; i<(OrdemMatriz); i++){
			MatrizX[i][0]=MatrizAumentada[i][OrdemMatriz]; // Criando Matriz X
		}
	for (int i=0; i<(OrdemMatriz); i++){
		for (int j=1;j<(OrdemMatriz);j++){
			MatrizX[i][j]=MatrizAumentada[i][j];
		}
	}
	
	for (int i=0; i<(OrdemMatriz); i++){
		MatrizY[i][1]=MatrizAumentada[i][OrdemMatriz]; // Criando Matriz Y
}
	for (int i=0; i<(OrdemMatriz); i++){
	for (int j=0;j<1;j++){
		MatrizY[i][j]=MatrizAumentada[i][j];
	}
}
	for (int i=0; i<(OrdemMatriz); i++){
		for (int j=2; j<(OrdemMatriz); j++){
			MatrizY[i][j]=MatrizAumentada[i][j];
		}					
	}
		
	if (OrdemMatriz==3){
	
		for (int i=0;i<(OrdemMatriz);i++){
		for(int j=0; j<(OrdemMatriz-1);j++){
			MatrizZ[i][j]=MatrizAumentada[i][j];  // Criando Matriz Z
		}
	}
	for (int i=0; i<(OrdemMatriz); i++){
		for (int j=0; j<(OrdemMatriz); j++){
			MatrizZ[i][OrdemMatriz-1]=MatrizAumentada[i][OrdemMatriz];
		}
	}
	}
	Determinante = determinante(MatrizCoeficientes, OrdemMatriz);
	DeterminanteX = determinante(MatrizX, OrdemMatriz);
	DeterminanteY = determinante(MatrizY, OrdemMatriz);
	DeterminanteZ = determinante(MatrizZ, OrdemMatriz);
	}
		
	
	public void solucionar(int OrdemMatriz, double[][] MatrizAumentada) { // Método Para Solucionar usando Cramer
		
		double x1 = (DeterminanteX/Determinante);
		double x2 = (DeterminanteY/Determinante);
		double x3 = (DeterminanteZ/Determinante);
		
		if (OrdemMatriz<2){
			System.out.println("Digite um Sistema Válido");
		}
		else if (OrdemMatriz==2){
			
			System.out.println("X1 = "+x1);
			System.out.println("X2 = "+x2);
		}
		else if (OrdemMatriz==3){
			System.out.println("X1 = "+x1);
			System.out.println("X2 = "+x2);
			System.out.println("X3 = "+x3);
		}
		else
			System.out.println("Use outro método pra resolver Sistemas dessa ordem espertão!");
		
	}
	
	@Override
	public void checarSist(int OrdemMatriz, double[][] MatrizAumentada) { //Método para Checar os resultados em Cramer
		if (OrdemMatriz == 3){
		if (Determinante == 0){
			if ((DeterminanteX==0)&&(DeterminanteY==0)&&(DeterminanteZ==0)){
				System.out.println("O sistema possui soluções infinitas");
			}
			if ((DeterminanteX!=0)||(DeterminanteY!=0)||(DeterminanteZ!=0)){
				System.out.println("Sistema Impossível");
			}
							 }
		else if (Determinante!=0){
			System.out.println("Sistema com uma solução"); //Sistema compatível determinado
		}
		}
		else if (OrdemMatriz == 2){
			if (Determinante == 0){
				if ((DeterminanteX==0)&&(DeterminanteY==0)){
					System.out.println("O sistema possui soluções infinitas");
				}
				if ((DeterminanteX!=0)||(DeterminanteY!=0)){
					System.out.println("Sistema Impossível");
				}
								 }
			else if (Determinante!=0){
				System.out.println("Sistema com uma solução"); //Sistema compatível determinado
			}
			}	
		}
	
	
	@Override
	public void calresíduo(double [][] MatrizAumentada, int OrdemMatriz) {
		if (OrdemMatriz ==2){
		double resíduo;
		resíduo = ((DeterminanteX/Determinante) * MatrizAumentada[0][0])+ ((DeterminanteY/Determinante) * MatrizAumentada[0][1]) - MatrizAumentada[0][OrdemMatriz];
		System.out.println("Erro residual = " + resíduo);
		}
		if (OrdemMatriz ==3){
			double resíduo;
			resíduo = ((DeterminanteX/Determinante) * MatrizAumentada[0][0])+ ((DeterminanteY/Determinante) * MatrizAumentada[0][1]) + ((DeterminanteX/Determinante) * MatrizAumentada[0][2]) - MatrizAumentada[0][OrdemMatriz];
			System.out.println("Erro residual = " + resíduo);
			}
	}
	
	private static double determinante(double Matriz[][], int N){ // Método recursivo para calcular determinantes
		
		double det=0;
        
		if(N == 1)
        {
            det = Matriz[0][0];
        }
        else if (N == 2)
        {
            det = Matriz[0][0]*Matriz[1][1] - Matriz[1][0]*Matriz[0][1];
        }
        else
        {
            det=0;
            for(int j1=0;j1<N;j1++)
            {
                double[][] m = new double[N-1][N-1];
                
                for(int i=1;i<N;i++)
                {
                    int j2=0;
                    for(int j=0;j<N;j++)
                    {
                        if(j == j1)
                            continue;
                        m[i-1][j2] = Matriz[i][j];
                        j2++;
                    }
                }
                det += Math.pow(-1.0,1.0+j1+1.0)* Matriz[0][j1] * determinante(m,N-1);
            }
        }
        return det;
    }

	public void CalculaTudo(int OrdemMatriz, double[][] MatrizAumentada){
		CalCramer a = new CalCramer(MatrizAumentada, OrdemMatriz);
		
		a.checarSist(OrdemMatriz, MatrizAumentada);
		a.solucionar(OrdemMatriz, MatrizAumentada);
		a.calresíduo(MatrizAumentada, OrdemMatriz);
	}
}
