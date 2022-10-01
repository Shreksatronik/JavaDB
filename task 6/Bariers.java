public class Bariers implements Runnable {

    Company company;

    public Bariers(Company company) {
        this.company = company;
    }

    @Override
    public void run() {
        company.showCollaborativeResult();
    }
}