import { AbstractControl, AsyncValidatorFn, ValidationErrors } from "@angular/forms";
import { catchError, delay, EMPTY, map, Observable } from "rxjs";
import {ajax} from 'rxjs/ajax'
import { environment } from "src/environments/environment";


export function userNameValidator () :AsyncValidatorFn{

  return (control: AbstractControl) : Observable<ValidationErrors | null> => {
    return checkUserName(control.value).pipe(
      map(res => {
        console.log("The value of the result from the result is ", res);
        return res === false ? null : {userNameExist : true}
      }),

      catchError((err) => {
        console.log("from the async validator an error has been catched!");
        return EMPTY;
      }),

    );

  };

  function checkUserName(userName: string) : Observable<boolean | any> {
    var apiServerUrl = environment.apiBaseUrl;
    return ajax.getJSON(`${apiServerUrl}/register/userNameExists/${userName}`).pipe(delay(1000));
  }
}
