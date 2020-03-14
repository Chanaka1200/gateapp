export class CardReportTransactionModel {
    private transactionId: number;
    private barcode: number;
    private checkIn: Date;
    private checkOut: Date;
    private debit: number;
    private credit: number;
    private date: Date;
    constructor(transactionId: number, barcode: number, checkIn: Date, checkOut: Date, debit: number, credit: number, date: Date){
        this.transactionId = transactionId;
        this.barcode = barcode;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.debit = debit;
        this.credit = credit;
        this.date = date;

    }
}