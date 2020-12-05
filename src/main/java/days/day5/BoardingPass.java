package days.day5;

public class BoardingPass {
    private String boardingPassID;
    private String binaryLeftSide;
    private String binaryRightSide;
    private int row;
    private int column;
    private int seatID;

    public String getBoardingPassID() {
        return boardingPassID;
    }

    public void setBoardingPassID(String boardingPassID) {
        this.boardingPassID = boardingPassID;

        this.binaryLeftSide = this.boardingPassID.substring(0,7);
        this.binaryRightSide = this.boardingPassID.substring(7);
    }

    public String getBinaryLeftSide() {
        return binaryLeftSide;
    }

    public void setBinaryLeftSide(String binaryLeftSide) {
        this.binaryLeftSide = binaryLeftSide;
    }

    public String getBinaryRightSide() {
        return binaryRightSide;
    }

    public void setBinaryRightSide(String binaryRightSide) {
        this.binaryRightSide = binaryRightSide;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }
}
