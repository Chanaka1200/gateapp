export class CardOperrationModel {
    public operationId: number;
    public checkIn: Date;
    public checkOut: Date;
    public status: string;
    public price: number;
    public paid: boolean;
    public barcode: number;
    public frontImage: string;
    public backImage: string;
    public frontImageFilePath: string;
    public backImageFilePath: string;
    public frontOutImage: string;
    public backOutImage: string;
    public frontOutImageFilePath: string;
    public backOutImageFilePath: string;
    constructor(operationId: number, checkIn: Date, checkOut: Date, status: string, price: number, paid: boolean, barcode: number, frontImage: string, backImage: string, frontImageFilePath: string, backImageFilePath: string, frontOutImage:string, backOutImage: string, frontOutImageFilePath: string, backOutImageFilePath: string){
        this.operationId = operationId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
        this.price = price;
        this.paid = paid;
        this.barcode = barcode;
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.frontImageFilePath = frontImageFilePath;
        this.backImageFilePath = backImageFilePath;
        this.frontOutImage = frontOutImage;
        this.backOutImage = backOutImage;
        this.frontOutImageFilePath = frontOutImageFilePath;
        this.backOutImageFilePath = backOutImageFilePath;
    }
}