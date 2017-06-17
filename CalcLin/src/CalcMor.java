public abstract class CalcMor {
	
	double [][] MatrizAumentada; // Matriz que guarda todos os elementos do sistema de equa��es
	
	int OrdemMatriz; // Ordem do sistema
		
	public abstract void solucionar(int OrdemMatriz, double[][] MatrizAumentada); // M�todo abstrato para solucionar o sistema nas calculadoras filhas
	
	public abstract void checarSist(int OrdemMatriz, double[][] MatrizAumentada); // M�todo para checar as condi��es do Sistema
	
	public abstract void calres�duo(double[][] MatrizAumentada, int OrdemMatriz); // M�todo para calcular res�duos	
}
