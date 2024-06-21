package sumago.androidipt.b2expensemanager.models;

public class Expense {
    private int id;
    private String name;
    private String categoryName;
    private String date;
    private double amount;

    private String note;
    private int categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Expense() {
    }

    public Expense(String name, String categoryName, String date, double amount, String note,int categoryId) {

        this.name = name;
        this.categoryName = categoryName;
        this.date = date;
        this.amount = amount;
        this.categoryId = categoryId;
        this.note=note;
    }
    public Expense(int id,String name, String categoryName, String date, double amount, String note,int categoryId) {

        this.name = name;
        this.categoryName = categoryName;
        this.date = date;
        this.amount = amount;
        this.categoryId = categoryId;
        this.note=note;
        this.id=id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
