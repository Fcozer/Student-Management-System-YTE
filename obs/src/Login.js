import React, {useState} from "react";
import { useNavigate } from "react-router-dom";
import { LoginApi } from "./LoginApi";
import { toast } from "react-toastify";

const loginApi = new LoginApi();
function Login() {
    const [formState, setFormState] = useState({});

    const navigate = useNavigate();

    function onFormInputChange(event) {
        const field = event.target.name;
        const value = event.target.value;
        const newState = { ...formState };
        console.log(newState);
        newState[field] = value;
        setFormState(newState);
    }

    async function login(event) {
        event.preventDefault();
        const response = await loginApi.login(formState);
        const SecurityRecord = response.data;
        const messageResponse = response.data;
        if (messageResponse.responseType === "SUCCESS") {
            toast.success(messageResponse.message);
            if(SecurityRecord.isAuthority === "ADMIN") {
                navigate("/Admin");
            }
            else if(SecurityRecord.isAuthority === "STUDENT") {
                navigate("/Student")
            }
            else if(SecurityRecord.isAuthority === "ASSISTANT") {
                navigate("/Assistant")
            }
            else if(SecurityRecord.isAuthority === "ACADEMICIAN") {
                navigate("/Academician")
            }

        }
        else{
            toast.error(messageResponse.message);
        }


    }

    return(
        <form>
            <div className='main'>
                <div className='sub-main'>
                    <div>
                        <div>
                            <h1 className='LHeader'>LOGIN</h1>
                            <div>
                                <input type="text" placeholder='Enter Username' className='fill'name="username" onChange={onFormInputChange} />
                            </div>
                            <div className='second-input'>
                                <input type="password" placeholder='Enter Password' className='fill' name="password" onChange={onFormInputChange}/>
                            </div>
                            <div className='login-btn'>
                                <button className="login" onClick={login}>
                                    Login
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    );
}
export default Login;