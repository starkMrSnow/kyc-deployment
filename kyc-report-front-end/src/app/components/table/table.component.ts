import { NgFor, NgIf } from '@angular/common';
import { Component, signal } from '@angular/core';
import { KycService, CustomerDto } from '../../services/kyc.service';

@Component({
  selector: 'app-table',
  imports: [ NgFor, NgIf],
  templateUrl: './table.component.html',
  styleUrl: './table.component.scss',
  providers: [KycService]
})
export class TableComponent {
 customers: CustomerDto[] = [];

 constructor(private kycService: KycService){}


  emailStatus = signal("");
  kycStatus = signal("");


  showHealthStatusModal = true;

  ngOnInit():void{
    this.fetchCustomers();
    this.checkServiceHealth();
  }

  
  selectedPerson: any = null;
  selectedImage: any = null;
  https: any;

  showDetails(person: any) 
  {
    console.log('Selected person:', person);
    this.selectedPerson = person;
  }

  closeModal() {
    this.selectedPerson = null;
  }

  generatePDF() {
  if (!this.selectedPerson?.email) {
    console.error('No selected person or email not available.');
    return;
  }

  const email = this.selectedPerson.email;
  this.kycService.exportCustomerToPdf(email).subscribe({
    next: (pdfBlob: Blob) => {
      const blobUrl = window.URL.createObjectURL(pdfBlob);
      const a = document.createElement('a');
      a.href = blobUrl;
      a.download = `${this.selectedPerson.name}_Details.pdf`;
      a.click();
      window.URL.revokeObjectURL(blobUrl);
    },
    error: (err) => {
      console.error('Error exporting PDF:', err);
    }
  });
}

   openImage(image: any) {
    this.selectedImage = image;
  }

 removeSelfie(email: string){
  this.kycService.removeCustomerSelfie(email).subscribe({
    next: (response) => {
      console.log(response);
    },
    error: (err) => {
      console.error('Failed to remove selfie', err);
    }
  })
 }

closeImage() {
  this.selectedImage = null;
}

fetchCustomers() {
  this.kycService.getAllCustomers().subscribe({
    next: (data) => {
      this.customers = data.sort((a, b) => a.customerId - b.customerId);
    },
    error: (err) => console.error('Failed to load customers:', err)
  });
}

checkServiceHealth() {
  this.kycService.getEmailServiceHealth('email').subscribe({
    next: (response: any ) => {
      console.log(response);
      this.emailStatus.set(response.status);
    },
    error: (err: any) => {
      console.warn('Email service check failed:', err);
      // Don’t override with 'Down' if backend isn't connected
    }
  });

  this.kycService.getKycServiceHealth('kyc').subscribe({
    next: (response: any) => {
      console.log(response);
      this.kycStatus.set(response.status);
    },
    error: (err: any) => {
      console.warn('KYC service check failed:', err);
      // Don’t override with 'Down' if backend isn't connected
    }
  });
}


}
