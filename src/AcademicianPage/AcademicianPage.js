import React, {Component} from 'react';
import AcademicianNavbar from "./AcademicianNavbar";
import {Button, Container, NavLink} from 'reactstrap';
import {blue} from "@mui/material/colors";

class AcademicianPage extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    componentDidMount() {
        fetch('/academicians')
            .then(response => response.json())
            .then(data => this.setState({academicians: data}));
    }

    render() {
        return (

                <div class="academician">


                    <AcademicianNavbar/>
                    <Container fluid>
                        <br/>
                        <h2>Hello! This is Academician Page. How is your day going?</h2>
                        <br/>

                        <NavLink href="/Exams">Exam Edit Page</NavLink>



                    </Container>
                </div>

        );
    }
}

export default AcademicianPage;