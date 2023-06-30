package boardgame;

public abstract class Piece {

	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}
	
	// DEFINE A OPERAÇÃO DE MOVIMENTOS POSSIVEIS
	public abstract boolean	[][] possibleMoves();
	
	//RETORNA TRUE OU FALSE SE A PEÇA PODE SER MOVIDA PARA A POSIÇÃO DESEJADA
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}

	//OPERAÇÃO PARA VERIFICAR SE EXISTE UM MOVIMENTO POSSIVEL PARA A PEÇA
	public boolean isThereAnyPossibleMoves() {
		boolean[][] mat = possibleMoves();
		for (int i=0; i<mat.length;i++) {
			for ( int j=0; j<mat.length;j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
}
