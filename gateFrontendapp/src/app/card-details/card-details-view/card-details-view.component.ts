import { Component, OnInit } from '@angular/core';
import { CardDetailsServiceService } from '../car-details-service/card-details-service.service';

@Component({
  selector: 'app-card-details-view',
  templateUrl: './card-details-view.component.html',
  styleUrls: ['./card-details-view.component.css']
})
export class CardDetailsViewComponent implements OnInit {

  barcodeId: number;
  barcodeInputValue: any; 
  selectStatus: any;
  selectCardType: any;
  selectCardPolicy: any;
  buttonDisable: boolean = false;
  saveType: boolean = false;

  constructor(
    public apiService: CardDetailsServiceService,
  ) { }


  ngOnInit() {
    this.buttonDisable = false;
    this.saveType = false;
  }

  cardDetailsSave(barcodeInputValue, selectStatus,selectCardType,selectCardPolicy){
    if(barcodeInputValue == null){
      alert("Please insert barcode");
      return
    }
    if(selectStatus == null){
      alert("Please select status");
      return
    }
    console.log("save type" + this.saveType);
    if(this.saveType == false){
      this.apiService.postCardDetails(barcodeInputValue, selectStatus,selectCardType,selectCardPolicy).subscribe(responce =>{
        this.buttonDisable = true;
        alert(responce);
      })
    } else if(this.saveType == true) {
      this.apiService.putCardDetails(this.barcodeId,barcodeInputValue, selectStatus,selectCardType,selectCardPolicy).subscribe(responce =>{
        this.buttonDisable = true;
        alert(responce);
      })
    } 
  }

  barcodeKeyChange(barcodeInputValue){
    //this.barcode = event;
    this.clearAll();
    console.log("barcode input" + barcodeInputValue);
  }

  clearAll(){
    this.selectStatus= null;
    this.selectCardType= null;
    this.selectCardPolicy= null;
    this.buttonDisable = false;
    this.saveType = false;
  }

  clear(){
     this.barcodeInputValue= null;
    this.clearAll(); 
  }


  getBarcodeDetails(barcodeInputValue){
    this.apiService.getCardDetails(barcodeInputValue).subscribe(responce => {
        console.log("barcode values" + JSON.stringify(responce));
        if(responce == null) {
          this.saveType = false;
        } else {
          this.saveType = true;
        this.barcodeId = responce.barcodeId
        this.barcodeInputValue = responce.barcode
        this.selectStatus = responce.status
        this.selectCardType = responce.cardType
        this.selectCardPolicy = responce.cardPolicy
        }
    })
  }

}
