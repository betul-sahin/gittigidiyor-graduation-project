import React from 'react'
import * as Yup from "yup";
import { Formik, Form,Field, ErrorMessage } from "formik";
import { FormField, FormGroup, FormCheckbox, Button, Label, Container } from "semantic-ui-react";
import axios from 'axios';

export default function LoanAdd() {
     const initialValues = { identificationNumber:"",
    firstName:"", lastName:"", phoneNumber:"", monthlyIncome:""};

    // dogrulama yapmamizi saglayan bir yapi
    const schema = Yup.object({
        identificationNumber: Yup.string().required("Zorunlu"),
        firstName: Yup.string().required("Zorunlu"),
        lastName: Yup.string().required("Zorunlu"),
        phoneNumber: Yup.string().required("Zorunlu"),
        monthlyIncome: Yup.number().required("Zorunlu")
    })

    const postData = (loan) => {
        console.log(loan)
        axios.post(`http://localhost:8080/api/v1/loans/`, loan)
    }

    return (
        <Container>
            <Formik 
                initialValues={initialValues} 
                validationSchema={schema}
                onSubmit = {(values)=>{
                    console.log("formik")
                    postData(values)
                }}
                >
                <Form className="ui form">
                    <FormGroup widths={2}>
                        <FormField>
                            <Field name="identificationNumber" placeholder="TC Identification Number"></Field>
                            <ErrorMessage name="identificationNumber" render={error => 
                                <Label pointing basic color="red" content={error}/>
                            }></ErrorMessage>
                        </FormField>
                    </FormGroup>
                    <FormGroup widths={3}>
                        <FormField>
                            <Field name="firstName" placeholder="First Name"></Field>
                            <ErrorMessage name="firstName" render={error =>
                                <Label pointing basic color="red" content={error}/>
                            }></ErrorMessage>
                        </FormField>
                        <FormField>
                            <Field name="lastName" placeholder="Last Name"></Field>
                            <ErrorMessage name="lastName" render={error =>
                                <Label pointing basic color="red" content={error}/>
                            }></ErrorMessage>
                        </FormField>
                    </FormGroup>
                    <FormGroup widths={3}>
                        <FormField>
                            <Field name="phoneNumber" placeholder="Phone Number"></Field>
                            <ErrorMessage name="phoneNumber" render={error => 
                                <Label pointing basic color="red" content={error}/>
                            }></ErrorMessage>
                        </FormField>
                        <FormField>
                            <Field name="monthlyIncome" placeholder="Monthly Income"></Field>
                            <ErrorMessage name="monthlyIncome" render={error => 
                                <Label pointing basic color="red" content={error}></Label>
                            }></ErrorMessage>
                        </FormField>
                    </FormGroup>
                    <FormGroup>
                        <FormCheckbox label='I accept the processing of my personal data within the scope specified in the Information.' checked={true} />
                    </FormGroup>
                    <FormGroup>
                        <Button color="green" type="submit">Apply Now</Button>
                    </FormGroup>
                </Form>
            </Formik>
        </Container>
    )
}