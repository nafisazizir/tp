package chching.record;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncomeListTest {

    static final String DESCRIPTION = "salary";
    static final String DATE = "1st apr 2023";
    static final float INCOME_VALUE = (float) 1000000;
    @Test
    void getExpenseCount_one_expectOne() {

        Income income0 = new Income(DESCRIPTION, DATE, INCOME_VALUE);
        IncomeList list = new IncomeList();
        list.addRecord(income0);
        assertEquals(1, list.getRecordCount());
    }

}
