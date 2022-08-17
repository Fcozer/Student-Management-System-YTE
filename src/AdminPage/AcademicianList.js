import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import { Link } from 'react-router-dom';

class AcademicianList extends Component {

    constructor(props) {
        super(props);
        this.state = {academicians: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/academicians')
            .then(response => response.json())
            .then(data => this.setState({academicians: data}));
    }

    async remove(id) {
        await fetch(`/academicians/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedAcademicians = [...this.state.academicians].filter(i => i.id !== id);
            this.setState({academicians: updatedAcademicians});
        });
    }

    render() {
        const {academicians} = this.state;

        const academicianList = academicians.map(academician => {
            return <tr key={academician.id}>
                <td>{academician.id}</td>
                <td style={{whiteSpace: 'nowrap'}}>{academician.name}</td>
                <td>{academician.surname}</td>
                <td>{academician.username}</td>
                <td>{academician.password}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="warning" tag={Link} to={"/academicians/" + academician.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(academician.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <>
            <div>
                <AppNavbar/>
                <Container fluid>
                   <br/>
                    <h3>Academician Edit Page</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="15%">Academician ID</th>
                            <th width="15%">Name</th>
                            <th width="15%">Surname</th>
                            <th width="15%">Username</th>
                            <th width="15%">Password</th>
                            <th width="25%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {academicianList}
                        </tbody>
                    </Table>
                    <div className="float-right">
                        <Button color="dark" tag={Link} to="/academicians/new">Add Academician</Button>
                    </div>

                </Container>
            </div>
            <div>

            </div>
            </>
        );
    }
}

export default AcademicianList;