import React, {Component} from 'react';
import {Button, Nav, Navbar, NavbarBrand, NavItem, NavLink} from 'reactstrap';
import {Link} from 'react-router-dom';

export default class AcademicianNavbar extends Component {
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


    render() {
        return <Navbar color="dark" dark expand="md">
            <NavbarBrand tag={Link} to="/academicianPage" className="me-auto"><strong>ACADEMICIAN</strong></NavbarBrand>

            <Nav navbar>

                <NavItem>
                    <NavLink href="/Exams">Exam</NavLink>
                </NavItem>

                <NavItem>
                    <NavLink href="/">Logout</NavLink>
                </NavItem>
            </Nav>

        </Navbar>;
    }
}