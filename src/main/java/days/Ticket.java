package days;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    List<Integer> ticketNumbers = new ArrayList<>();

    public void addTicketNumber(int value) {
        ticketNumbers.add(value);
    }

    public List<Integer> getTicketNumbers() {
        return ticketNumbers;
    }

    public int getValueFromPos(int position) {
        return ticketNumbers.get(position);
    }
}
