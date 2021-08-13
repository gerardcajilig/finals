public class Employee {

    private String empNum;
    private String empName;
    private String monthlyIncome;
    private String member;
    private String status;

    //string was used, if it is going to be used for computation, we would convert it to Int.
    //or vice versa


    public Employee(String empNum, String empName, String monthlyIncome, String member, String status) {
        this.empNum = empNum;
        this.empName = empName;
        this.monthlyIncome = monthlyIncome;
        this.member = member;
        this.status = status;
    }

    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
