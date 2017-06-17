public abstract class CalcMor {
	
	double [][] MatrizAumentada; // Matriz que guarda todos os elementos do sistema de equações
	
	int OrdemMatriz; // Ordem do sistema
		
	public abstract void solucionar(int OrdemMatriz, double[][] MatrizAumentada); // Método abstrato para solucionar o sistema nas calculadoras filhas
	
	public abstract void checarSist(int OrdemMatriz, double[][] MatrizAumentada); // Método para checar as condições do Sistema
	
	public abstract void calresíduo(double[][] MatrizAumentada, int OrdemMatriz); // Método para calcular resíduos	
}
