import React from 'react'
import * as Yup from "yup";
import { Formik, Form,Field, ErrorMessage } from "formik";
import { FormField, Button, Label, Container } from "semantic-ui-react";

export default function LoanAdd() {
    const initialValues = { customerId: 1, creditAmount: 10, creditLimitMultiplier:4, creditResult:"APPROVAL"};

    // dogrulama yapmamizi saglayan bir yapi
    const schema = Yup.object({
        customerId: Yup.number().required("Zorunlu"),
        creditAmount: Yup.number().required("Zorunlu"),
        creditLimitMultiplier: Yup.number().required("Zorunlu"),
        creditResult: Yup.string().required("Zorunlu")
    })

    return (
        <Container>
            <Formik 
                initialValues={initialValues} 
                validationSchema={schema}
                onSubmit = {(values)=>{
                    console.log(values)
                }}
                >
                <Form className="ui form">
                    <FormField>
                        <Field name="customerId" placeholder="customer Id"></Field>
                        <ErrorMessage name="customerId" render={error => 
                            <Label pointing basic color="red" content={error}></Label>
                        }></ErrorMessage>
                    </FormField>
                    <FormField>
                        <Field name="creditAmount" placeholder="credit Amount"></Field>
                        <ErrorMessage name="creditAmount" render={error => 
                            <Label pointing basic color="red" content={error}></Label>
                        }></ErrorMessage>
                    </FormField>
                    <Button color="green" type="submit">Ekle</Button>
                </Form>
            </Formik>
        </Container>
    )
}
