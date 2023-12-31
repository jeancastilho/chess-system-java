package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;
//Classe da peça "Torre"


public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		
		Position p = new Position(0, 0);
		
		//VERIFICA AS POSIÇÕES DISPONIVEIS ACIMA
		p.setValues(position.getRow() - 1, position.getColumn());
		//LOOP PARA REPETIR ENQUANTO HA CASAS VAZIAS
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { 
			mat [p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		//VERIFICAR SE AINDA HA CASA DISPONIVEL E SE TEM PEÇA ADVERSARIA	
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		//VERIFICA AS POSIÇÕES DISPONIVEIS NA ESQUERDA
		p.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { 
			mat [p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}	
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		//VERIFICA AS POSIÇÕES DISPONIVEIS NA DIRETA
		p.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { 
			mat [p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}	
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		// VERIFICA AS POSIÇÕES DISPONIVEIS PARA BAIXO
		p.setValues(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}
}
