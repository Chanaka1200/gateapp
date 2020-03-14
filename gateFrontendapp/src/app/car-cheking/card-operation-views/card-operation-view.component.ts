import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { Observable } from 'rxjs/Observable';
import { WebcamImage, WebcamInitError, WebcamUtil } from 'ngx-webcam';
import { saveAs } from 'file-saver';
import * as uuid from 'uuid';
import { CardOperationService } from '../card-operation-service/card-operation-service.service';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-car-operation',
  templateUrl: './card-operation-view.component.html',
  styleUrls: ['./card-operation-view.component.css']
})
export class CardOperationViewComponent implements OnInit {

  // toggle webcam on/off
  public showWebcam = true;
  public allowCameraSwitch = true;
  public multipleWebcamsAvailable = false;
  public deviceId: string;
  public videoOptions: MediaTrackConstraints = {
    // width: {ideal: 1024},
    // height: {ideal: 576}
  };
  public errors: WebcamInitError[] = [];

  // latest snapshot
  public webcamImage: WebcamImage = null;

  // webcam snapshot trigger
  private trigger: Subject<void> = new Subject<void>();
  // switch to next / previous / specific webcam; true/false: forward/backwards, string: deviceId
  private nextWebcam: Subject<boolean | string> = new Subject<boolean | string>();

  // Checking Data
  dataOperationId: number;
  dataBarcode: number;
  dataCheckIn: Date;
  dataCheckOut: Date;
  dataStatus: string = "active";
  dataPrice: number = 0.00;
  dataPaid: boolean = false;
  dataFrontImage: string;
  dataBackImage: string;
  datafrontImageFilePath: string = "";
  dataBackImageFilePath: string = "";
  dataFrontOutImage: string;
  dataBackOutImage: string;
  datafrontOutImageFilePath: string = "";
  databackOutImageFilePath: string = "";

  // CheckOut Data
  outOperationId: number;
  outBarcode: number;
  outCheckIn: Date;
  outCheckOut: Date;
  outStatus: string;
  outPrice: number;
  outPaid: boolean;
  outFrontImage: string;
  outBackImage: string;
  outfrontImageFilePath: string = "";
  outBackImageFilePath: string = "";
  outFrontOutImage: string;
  outBackOutImage: string;
  outfrontOutImageFilePath: string;
  outbackOutImageFilePath: string;
  outTotalPrice:number = 0.00;
  outBalance:number = 0.00;

  displayPay: boolean = false;
  enablePay: boolean = false;
  btnSaveDisable: boolean = false;
  btnUpdateDisable: boolean = false;
  btnPrint: boolean = true;
  checkOutBarcode: string;
  inputCashValue: number;

  frontImageLocal: string = 'https://dummyimage.com/200x300/000/fff.jpg';
  backImageLocal: string = 'https://dummyimage.com/200x300/000/fff.jpg';
  frontOutImageLocal: string = 'https://dummyimage.com/200x300/000/fff.jpg'
  backOutImageLocal: string = 'https://dummyimage.com/200x300/000/fff.jpg'


  public deltas: string;

  constructor(
    public apiService: CardOperationService,
    public datePipe: DatePipe,
    private http: HttpClient
  ) { }

  public ngOnInit(): void {
    this.dataPrice = 0.00;
    this.btnPrint = true;
    this.frontImageLocal = 'https://dummyimage.com/200x300/000/fff.jpg';
    this.backImageLocal = 'https://dummyimage.com/200x300/000/fff.jpg';
    this.frontOutImageLocal = 'https://dummyimage.com/200x300/000/fff.jpg'
    this.backOutImageLocal = 'https://dummyimage.com/200x300/000/fff.jpg'
    WebcamUtil.getAvailableVideoInputs()
      .then((mediaDevices: MediaDeviceInfo[]) => {
        this.multipleWebcamsAvailable = mediaDevices && mediaDevices.length > 1;
      });
  }

  public triggerSnapshot(dataBarcode): void {
    if (dataBarcode == null || dataBarcode == '') {
      alert("Enter barcode plesase");
      return;
    }
    this.apiService.getOperationDetailsByBarcode(dataBarcode).subscribe(responce => {
      console.log("get barcode responce" + JSON.stringify(responce));
      this.trigger.next();
      this.dataOperationId = responce.operationId;
      this.dataCheckIn = responce.checkIn;
      this.dataCheckOut = responce.checkOut;
      this.dataPrice = responce.price;
      this.dataPaid = responce.paid;
      this.dataFrontImage = responce.frontImage;
      this.dataBackImage = responce.backImage;
      this.datafrontImageFilePath = responce.frontImageFilePath;
      this.dataBackImageFilePath = responce.backImageFilePath;
      this.dataFrontOutImage = responce.frontOutImage;
      this.dataBackOutImage = responce.backOutImage;
      this.datafrontOutImageFilePath = responce.frontOutImageFilePath;
      this.databackOutImageFilePath = responce.backOutImageFilePath;
    });
  }

  public saveOperation(base64String) {
    // Split the base64 string in data and contentType
    var block = base64String.split(";");
    // Get the content type
    var dataType = block[0].split(":")[1];// In this case "image/png"
    // get the real base64 content of the file
    var realData = block[1].split(",")[1];
    this.dataPrice = 0.00;
    console.log("data price" + this.dataPrice);
    this.apiService.postOperationDetails(this.dataBarcode, this.dataStatus, this.dataPrice.toFixed(2), realData, realData, realData, realData).subscribe(response => {
      console.log("responce save data" + JSON.stringify(response));
      this.btnSaveDisable = true;
      alert(response);
    });
  }

  updateOperation(){
    if(this.inputCashValue == null){
      alert("Enter cash amount");
      return
    }
    if(this.outBalance < 0) {
      alert("Wrong cash balance check again");
      return
    }
    this.apiService.putOperationDetails(this.outOperationId, this.outCheckIn, this.outCheckOut, this.outStatus,this.dataPrice.toFixed(2), true, this.outBarcode,this.outFrontImage, this.outBackImage, this.outfrontImageFilePath, this.outBackImageFilePath, this.outFrontOutImage, this.outBackOutImage, this.outfrontOutImageFilePath, this.outbackOutImageFilePath).subscribe(
      data => {
        this.btnUpdateDisable = true;
        this.btnPrint = false;
        alert(data);
        this.getCardDetails(this.outBarcode);
      },
      error => console.log(error),
      () => console.log("compelete"));
  }


  public toggleWebcam(): void {
    this.showWebcam = !this.showWebcam;
  }

  public handleInitError(error: WebcamInitError): void {
    this.errors.push(error);
  }

  public showNextWebcam(directionOrDeviceId: boolean | string): void {
    // true => move forward through devices
    // false => move backwards through devices
    // string => move to device with given deviceId
    this.nextWebcam.next(directionOrDeviceId);
  }

  public handleImage(webcamImage: WebcamImage): void {
    console.info('received webcam image', webcamImage);
    this.webcamImage = webcamImage;
  }

  public cameraWasSwitched(deviceId: string): void {
    console.log('active device: ' + deviceId);
    this.deviceId = deviceId;
  }

  public get triggerObservable(): Observable<void> {
    return this.trigger.asObservable();
  }

  public get nextWebcamObservable(): Observable<boolean | string> {
    return this.nextWebcam.asObservable();
  }

  public base64ToBlob(b64Data, contentType = '', sliceSize = 512) {
    b64Data = b64Data.replace(/\s/g, ''); //IE compatibility...
    let byteCharacters = atob(b64Data);
    let byteArrays = [];
    for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
      let slice = byteCharacters.slice(offset, offset + sliceSize);

      let byteNumbers = new Array(slice.length);
      for (var i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
      }
      let byteArray = new Uint8Array(byteNumbers);
      byteArrays.push(byteArray);
    }
    return new Blob(byteArrays, { type: contentType });
  }

  public getBase64(event) {
    let me = this;
    let file = event;
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      //me.modelvalue = reader.result;
      console.log(reader.result);
    };
    reader.onerror = function (error) {
      console.log('Error: ', error);
    };
 }

  viewPay() {
    this.frontImageLocal = "http://192.168.1.64/Streaming/Channels/101/picture";
    //this.getBase64("http://192.168.1.64/Streaming/Channels/101/picture");
    //console.log(this.frontImageLocal);
    this.http.get("http://admin:123456Lk@192.168.1.64/Streaming/Channels/101/picture", { responseType: 'blob' }).subscribe((resp: any) => {
      console.log(resp);  
    });

    if(this.outBarcode == null){
      alert("Get barcode details");
      return
    }
    var start = this.datePipe.transform(this.outCheckIn, "yyyy-MM-dd HH:mm:ss");
    var out = this.datePipe.transform(new Date(), "yyyy-MM-dd HH:mm:ss");
    var hours = this.calHours(start, out);
    this.dataPrice = hours * 10;
    this.dataPrice = Number(this.dataPrice.toFixed(2));
    this.displayPay = true;
    this.outCheckOut = new Date(out);
    this.outTotalPrice = this.dataPrice;
    this.outPrice = this.dataPrice;
    console.log("totoal price" + this.outTotalPrice);
  }

  calHours(start, out){
    var d1 = new Date(start);
    var d2 = new Date(out);
    return this.diff_hours(d1, d2);
  }
  private diff_hours(dt2, dt1){

  var diff =(dt2.getTime() - dt1.getTime()) / 1000;
  diff /= (60 * 60);
  return Math.abs(Math.round(diff));
  
  }

  balanceCalculate(inputCashValue){
    this.outBalance = Number((this.outTotalPrice - inputCashValue).toFixed(2));
  }

  clearChecking() {
    this.dataOperationId = null;
    this.dataBarcode = null;
    this.dataCheckIn = null;
    this.dataCheckOut = null;
    this.dataPrice = null;
    this.dataPaid = null;
    this.dataFrontImage = null;
    this.dataBackImage = null;
    this.datafrontImageFilePath = "";
    this.dataBackImageFilePath = "";
    this.webcamImage = null;
    this.btnSaveDisable = false;
    this.dataFrontOutImage = null;
    this.dataBackOutImage = null;
    this.datafrontOutImageFilePath = "";
    this.databackOutImageFilePath = "";
  }

  clearCheckOut() {
    this.displayPay = false;
    this.outBarcode = null;
    this.outOperationId = null;
    this.outCheckIn = null;
    this.outCheckOut = null;
    this.outPrice = null;
    this.outPaid = null;
    this.outFrontImage = null;
    this.outBackImage = null;
    this.outfrontImageFilePath = null;
    this.outBackImageFilePath = null;
    this.outTotalPrice = null;
    this.outBalance = null;
    this.btnUpdateDisable = false;
    this.btnPrint = true;
    this.inputCashValue = null;
    this.outFrontOutImage = null;
    this.outBackOutImage = null;
    this.outfrontOutImageFilePath = null;
    this.outbackOutImageFilePath = null;
    this.frontImageLocal = 'https://dummyimage.com/200x300/000/fff.jpg';
    this.backImageLocal = 'https://dummyimage.com/200x300/000/fff.jpg';
    this.frontOutImageLocal = 'https://dummyimage.com/200x300/000/fff.jpg'
    this.backOutImageLocal = 'https://dummyimage.com/200x300/000/fff.jpg'
  }

  clearCheckOutButton(){
    this.checkOutBarcode = null;
    this.clearCheckOut();
  }

  getCardDetails(checkOutBarcode) {
    this.apiService.getOperationDetailsByBarcode(checkOutBarcode).subscribe(responce => {
      console.log("get barcode responce" + JSON.stringify(responce));
      this.outBarcode = checkOutBarcode;
      this.outOperationId = responce.operationId;
      this.outCheckIn = responce.checkIn;
      //this.outCheckInSt = this.datePipe.transform(new Date(), "yyyy-MM-dd");
      this.outCheckOut = responce.checkOut;
      this.outStatus = responce.status;
      this.outPrice = responce.price;
      this.outPaid = responce.paid;
      this.outFrontImage = responce.frontImage;
      this.outBackImage = responce.backImage;
      this.outfrontImageFilePath = responce.frontImageFilePath;
      this.outBackImageFilePath = responce.backImageFilePath;
      this.outFrontOutImage = responce.frontOutImage;
      this.outBackOutImage = responce.backOutImage;
      this.outfrontOutImageFilePath = responce.frontOutImageFilePath;
      this.outbackOutImageFilePath = responce.backOutImageFilePath;
      this.frontImageLocal = 'data:image/jpeg;base64,' + this.outFrontImage;
      this.backImageLocal = 'data:image/jpeg;base64,' + this.outBackImage;
      this.frontOutImageLocal = 'data:image/jpeg;base64,' + this.outFrontOutImage;
      this.backOutImageLocal = 'data:image/jpeg;base64,' + this.outBackOutImage;
      console.log("fornt" + this.outFrontImage)
    });
  }

  barcodeGetKeyChange(){
    this.clearCheckOut();
  }
}
