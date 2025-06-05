import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideHttpClient } from '@angular/common/http';
import { KycService } from './app/services/kyc.service';

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(),
    KycService
  ]
});
