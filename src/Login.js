import {Component} from "react";
import {
    Form,
    FormGroup,
    Label,
    Input,
    Button,
} from 'reactstrap';
import './Css/Login.css';
 

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: ''

        };
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange = (event) => {
        const {target} = event;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const {name} = target;

        this.setState({
            [name]: value,
        });
    };


    submitForm(e) {
        e.preventDefault();
        console.log(`Username: ${this.state.email}`);
    }

    render() {
        const {username, password} = this.state;

        return (

            <div className="Login">


                <h2 className={"sign"}>Sign In</h2>
                <Form className="form" onSubmit={(e) => this.submitForm(e)}>
                    <FormGroup>
                        <Label>Username</Label>
                        <Input
                            type="username"
                            name="username"
                            id="username"
                            placeholder="username"

                            value={username}
                            onChange={(e) => {

                                this.handleChange(e);
                            }}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label for="examplePassword">Password</Label>
                        <Input
                            type="password"
                            name="password"
                            id="examplePassword"
                            placeholder="********"
                            value={password}
                            onChange={(e) => this.handleChange(e)}
                        />
                    </FormGroup>
                    <Button className={"button"} href="/admin">Submit</Button>
                </Form>
            </div>
        );
    }
}

export default Login;



