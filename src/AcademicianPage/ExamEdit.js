import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AcademicianNavbar from "./AcademicianNavbar";

class ExamEdit extends Component {

    emptyItem = {
        examClass: '',
        startTimeSlot: '',
        endTimeSlot: '',
        lessonId:''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const exam = await (await fetch(`/Exams/${this.props.match.params.id}`)).json();
            this.setState({item: exam});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;

        await fetch('/Exams' + (item.id ? '/' + item.id : ''), {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/Exams');
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit exam' : 'Add Exam'}</h2>;

        return <div>
            <AcademicianNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="examClass">Exam Name</Label>
                        <Input type="text" name="examClass" id="examClass" value={item.examClass || ''}
                               onChange={this.handleChange} autoComplete="examClass"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="startTimeSlot">Exam Start Time</Label>
                        <Input type="time" name="startTimeSlot" id="startTimeSlot" value={item.startTimeSlot || ''}
                               onChange={this.handleChange} autoComplete="startTimeSlot"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="endTimeSlot">Exam End Time</Label>
                        <Input type="time" name="endTimeSlot" id="endTimeSlot" value={item.endTimeSlot || ''}
                               onChange={this.handleChange} autoComplete="endTimeSlot"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="lessonId">lessonId</Label>
                        <Input type="text" name="lessonId" id="lessonId" value={item.lessonId || ''}
                               onChange={this.handleChange} autoComplete="lessonId"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/Exams">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(ExamEdit);