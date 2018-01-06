public class BusChallanModel {
    private String BookingNo;
    private String TicketNo;
    private String PaymentMode;
    private String SeatNo;
    private String BoardingPoint;
    private String Fullname;
    private String OperatorName;
    private String TotalAmt;
    private String Route;
    private String Remarks;
    private String MobileNo;

    public BusChallanModel(String bookingNo, String ticketNo, String fullname, String mobileNo, String boardingPoint, String paymentMode, String totalAmt, String seatNo, String remarks, String route, String operatorName) {
        ///SerialNo=serialNo;
        TicketNo = ticketNo;
        SeatNo = seatNo;
        PaymentMode = paymentMode;
        TotalAmt = totalAmt;
        Fullname = fullname;
        OperatorName = operatorName;
        Route = route;
        Remarks = remarks;
        MobileNo = mobileNo;
        BoardingPoint = boardingPoint;
        BookingNo = bookingNo;
    }

    /* public String getSerialNo() {
         return SerialNo;
     }
 */
    public String getTicketNo() {
        return TicketNo;
    }

    public String getSeatNo() {
        return SeatNo;
    }

    public String getPaymentMode() {
        return PaymentMode;
    }

    public String getTotalAmt() {
        return TotalAmt;
    }

    public String getBoardingPoint() {
        return BoardingPoint;
    }

    public String getFullname() {
        return Fullname;
    }

    public String getOperatorName() {
        return OperatorName;
    }

    public String getRoute() {
        return Route;
    }

    public String getRemarks() {
        return Remarks;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public String getBookingNo() {
        return BookingNo;
    }
}
