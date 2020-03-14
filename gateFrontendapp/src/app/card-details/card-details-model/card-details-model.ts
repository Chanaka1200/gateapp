export class CardDetailsModel {
    public barcodeId: number;
    public barcode: number;
    public issueDate: Date;
    public status: string;
    public cardType: string;
    public cardPolicy: string;
    constructor(barcodeId: number, barcode: number, issueDate: Date, status: string, cardType: string, cardPolicy: string){
        this.barcodeId = barcodeId;
        this.barcode = barcode;
        this.issueDate = issueDate;
        this.status = status;
        this.cardType = cardType;
        this.cardPolicy = cardPolicy;
    }
}