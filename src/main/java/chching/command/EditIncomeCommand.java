package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.parser.Incomes;
import chching.record.ExpenseList;
import chching.record.Income;
import chching.record.IncomeList;
import chching.record.TargetStorage;

import java.util.HashMap;

public class EditIncomeCommand extends Command {
    private int index;
    private HashMap<String, String> argumentsByField;
    private boolean hasDescription;
    private boolean hasDate;
    private boolean hasValue;
    
    public EditIncomeCommand(HashMap<String, String> argumentsByField) throws ChChingException {
        this.argumentsByField = argumentsByField;
        
        index = Incomes.getIndex(argumentsByField);
        hasDescription = argumentsByField.containsKey("de");
        hasDate = argumentsByField.containsKey("da");
        hasValue = argumentsByField.containsKey("v");
    }
    
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage, Selector selector,
                        Converter converter, TargetStorage targetStorage) throws ChChingException {
        // check if the index is valid
        if (index <= 0) {
            throw new ChChingException("Negative/Zero index");
        } else if (index > incomes.size()) {
            throw new ChChingException("The index is too big");
        }
        assert index > 0 : "Index must be a positive integer";
        
        if (!hasDescription && !hasDate && !hasValue) {
            throw new ChChingException("No fields to edit");
        }
        // change from 1-based indexing to 0-based indexing
        int indexZeroBased = index - 1;
        Income income = incomes.get(indexZeroBased);
        
        // edit the fields accordingly
        if (hasDescription) {
            String value = argumentsByField.get("de");
            incomes.editIncome(index, "de", value);
        }
        if (hasDate) {
            String value = argumentsByField.get("da");
            incomes.editIncome(index, "da", value);
        }
        if (hasValue) {
            String value = argumentsByField.get("v");
            incomes.editIncome(index, "v", value);
        }
        
        boolean isExpense = false;
        ui.showEdited(index, income, isExpense);
    }
}
