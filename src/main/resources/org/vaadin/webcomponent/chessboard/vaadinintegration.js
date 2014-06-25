

var el =  document.createElement("link");
el.rel = "import";
el.href="VAADIN/chessstuff/chess-board/dist/chess-board.html";

document.getElementsByTagName("head").item(0).appendChild(el);


org_vaadin_webcomponent_chessboard_ChessBoard = function() {
  var e = this.getElement();
	
  this.setBoardState = function(boardState) {
    e.innerHTML = "<chess-board>" + boardState + "</chess-board>";
  };
  
  this.move = function(from, to) {
      e.firstChild.move(from, to);
  };
  
};

