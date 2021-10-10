import React from 'react'
import * as Yup from "yup";
import { Formik, Form,Field, ErrorMessage } from "formik";
import { FormField, Button, Label, Container } from "semantic-ui-react";
import axios from 'axios';

export default function LoanAdd() {
    const initialValues = { creditLimitMultiplier:4, identificationNumber:"11111111111"};

    // dogrulama yapmamizi saglayan bir yapi
    const schema = Yup.object({
        creditLimitMultiplier: Yup.number().required("Zorunlu"),
        identificationNumber: Yup.string().required("Zorunlu")
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
                    <FormField>
                        <Field name="identificationNumber" placeholder="identificationNumber Id"></Field>
                        <ErrorMessage name="identificationNumber" render={error => 
                            <Label pointing basic color="red" content={error}></Label>
                        }></ErrorMessage>
                    </FormField>
                    <FormField>
                        <Field name="creditLimitMultiplier" placeholder="creditLimitMultiplier Amount"></Field>
                        <ErrorMessage name="creditLimitMultiplier" render={error => 
                            <Label pointing basic color="red" content={error}></Label>
                        }></ErrorMessage>
                    </FormField>
                    <Button color="green" type="submit">Apply Now</Button>
                </Form>
            </Formik>
        </Container>
    )
}
