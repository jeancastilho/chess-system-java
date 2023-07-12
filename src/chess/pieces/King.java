package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

//CLASSE DA PEÇA "REI"

public class King extends ChessPiece {

	private ChessMatch chessMatch;
	public King(Board board, Color color, ChessMatch chessMatch) {

		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	//METODO AUXILIAR PARA SABER SE A PEÇA "REI" PODE MOVER PARA ALGUM LUGAR
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	//METODO PARA SABER SE O "REI" ESTÁ APTO A JOGADA "ROQUE"
	private boolean	testRookCastling(Position position){
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return  p != null && p instanceof  Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//VERIFICA AS POSIÇÕES DISPONIVEIS ACIMA
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//VERIFICA AS POSIÇÕES DISPONIVEIS ABAIXO
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// VERIFICA AS POSIÇÕES DISPONIVEIS ESQUERDA
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// VERIFICA AS POSIÇÕES DISPONIVEIS DIREITA
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// VERIFICA AS POSIÇÕES DISPONIVEIS NO NOROESTE (DIAGONAL SUPERIOR ESQUEDA )
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// VERIFICA AS POSIÇÕES DISPONIVEIS NO NORDESTE (DIAGONAL SUPERIOR DIREITA )
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// VERIFICA AS POSIÇÕES DISPONIVEIS NO SUDOESTE (DIAGONAL INFERIOR ESQUERDA )
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// VERIFICA AS POSIÇÕES DISPONIVEIS NO SUDESTE (DIAGONAL INFERIOR DIREITA )
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// SPECIAL MOVE CASTLING
		if(getMoveCount() == 0 && !chessMatch.getCheck()){
			//#SPECIALMOVE CASTLING KINGSIDE ROOK
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
			if(testRookCastling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null){
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}

			//#SPECIALMOVE CASTLING QUEENSIDE ROOK
			Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
			if(testRookCastling(posT2)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null &&  getBoard().piece(p3) == null){
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}
		return mat;
	}

}
