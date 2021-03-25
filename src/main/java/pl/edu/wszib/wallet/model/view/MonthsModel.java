package pl.edu.wszib.wallet.model.view;

import java.util.ArrayList;

public class MonthsModel {

    private Months months;

    public Months getMonths() {
        return months;
    }

    public void setMonths(Months months) {
        this.months = months;
    }

    public ArrayList<String> getMonthsToList() {
        ArrayList<String> months = new ArrayList<>();

        for(Months month : Months.values()) {
            months.add(month.toString());
        }

        return months;
    }

    public enum Months {
        Styczen,
        Luty,
        Marzec,
        Kwiecien,
        Maj,
        Czerwiec,
        Lipiec,
        Sierpien,
        Wrzesien,
        Pazdziernik,
        Listopad,
        Grudzien
    }


}
