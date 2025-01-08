import {Component, inject, OnInit, signal, WritableSignal} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SignUpRequest} from '../../core/requests/sign-up.request';
import {UserService} from '../../core/services/user.service';
import {SOME_THING_WENT_WRONG} from '../../core/constants/message';

@Component({
  selector: 'app-register',
  standalone: false,

  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent implements OnInit{

  private formBuilder = inject(FormBuilder);
  signUpForm!: FormGroup;

  hide = signal(true);
  hide1 = signal(true);
  private responseMessage!: string;

  constructor(private userService:UserService) {
  }

  clickEvent(event: MouseEvent, sig:WritableSignal<boolean>) {
    sig.set(!sig());
    event.stopPropagation();
  }

  ngOnInit() {
    this.signUpForm = this.formBuilder.group({
      firstname: [
        null,
        Validators.required
      ],

      lastname: [
        null,
        Validators.required
      ],
      username: [
        null,
        Validators.required
      ],
      email: [
        null,
        Validators.required
      ],
      phoneNumber: [
        null,
        Validators.required
      ],
      password:[
        null,
        [Validators.required]
      ],
      confirmPassword:[
        null,
        [Validators.required]
      ]
    });
  }

  handleSubmit(){
    // this.ngxService.start();
    console.log(this.signUpForm.value);
    let formData : SignUpRequest = this.signUpForm.value;
    console.log(formData);
    this.userService.signup(formData).subscribe({
      next : value => {
        // this.ngxService.stop();
        this.responseMessage = value.message;
        // this.snackbarService.openSnackBar(this.responseMessage, "");
        // this.router.navigate(['/'])
      },
      error: err => {
        console.log(err)
        // this.ngxService.stop();
        // const errors = err?.error;

        // let er = false;
        // for (const fieldError in errors) {
        //   if (errors[fieldError]) {
        //     this.signupForm.controls[fieldError].setErrors({invalid: errors[fieldError]});
        //   }
        // }
        // if(!this.signupForm.valid){
        //   er = true;
        //   this.responseMessage =  "Request is not valid!";
        // }
        // if(err.error?.message){
        //   this.responseMessage = err.error.message;
        // }else if(!er){
        //   this.responseMessage = SOME_THING_WENT_WRONG
        // }
        // this.snackbarService.openSnackBar(this.responseMessage, GlobalConstants.error)
      }
    });
  }

  validateSubmit() {
    return this.password?.value != this.confirmPassword?.value;
  }



  // get firstname(){
  //   return this.signUpForm.get('firstname');
  // }

  // get lastname(){
  //   return this.signUpForm.get('lastname');
  // }
  //
  // get username(){
  //   return this.signUpForm.get('username');
  // }
  //
  // get email(){
  //   return this.signUpForm.get('email');
  // }
  //
  // get contactNumber(){
  //   return this.signUpForm.get('contactNumber');
  // }

  get password(){
    return this.signUpForm.get('password');
  }

  get confirmPassword(){
    return this.signUpForm.get('confirmPassword');
  }
}
