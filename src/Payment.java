public class Payment {

    private final String paymentID;
    private final double amount;
    private final String method;
    private  String status;

    public Payment(String paymentID,
                   double amount,
                   String method) {

        this.paymentID = paymentID;
        this.amount = amount;
        this.method = method;
        this.status = "Pending";
    }

    public String getPaymentID() {
        return paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public String getStatus() {
        return status;
    }

    public void processPayment()
            throws InvalidPaymentException {

        if (amount <= 0) {
            throw new InvalidPaymentException(
                    "Payment amount must be greater than zero.");
        }

        if (method == null || method.trim().isEmpty()) {
            throw new InvalidPaymentException(
                    "Payment method is required.");
        }

        status = "Paid";
    }

    public void refund() {

        if (status.equals("Paid")) {
            status = "Refunded";
        }
    }
}
