import Card from 'react-bootstrap/Card';
import axios from "axios";
import "../App.scss";
import React, {useEffect, useRef, useState} from "react";
import {Accordion, Button, Col, Placeholder, Row} from "react-bootstrap";
import {EndNav} from "./endNav";
import Logo from "./logo";


function BookShelf() {
    const [isLoading, setIsLoading] = useState(true);
    const [listData, setListData] = useState([]);
    const [isbnToDelete, setIsbnToDelete] = useState("");
    const ref = useRef(null);
    useEffect(
        () => {
            axios.get("/booklist")
                .then(res => {
                    console.log(res.data);
                    setListData(res.data);
                    setIsLoading(false);
                })
                .catch(error => console.log(error));
        }, []
    )

    function deleteBook() {
        axios.post("/booklist/delete/"+isbnToDelete, )
            .then(res => {
                console.log(res.data);
            // Hide ref after here
            })
            .catch(error => console.log(error));
    }

    return (
        <div id="topBar">
            <div className="px-5 pt-2 pb-2 d-flex flex-row" styles={{gap: 10 + 'px'}} id="nav">
                <Logo/>
                <div>
                    <h1>Bookshelf</h1>
                </div>
                <div className="ps-5 col">
                    <EndNav/>
                </div>
            </div>

            <div className="container overflow-hidden text-center p-5" id="bookShelf">
                <Row className="g-1" id="Shelf">

                    {isLoading ?
                        <CardLoading/>
                        : listData.map((data, id) => {
                                return (
                                    <Col key={id}>
                                        <Card style={{width: '18rem'}} ref={id}>
                                            <Card.Img variant="top" src={data.cover} width={286} height={360}/>
                                            <Card.Body>
                                                <Accordion>
                                                    <Accordion.Item eventKey="0">
                                                        <Accordion.Header>{data.title}</Accordion.Header>
                                                        <Accordion.Body>
                                                            {data.description}
                                                            {<br></br>}
                                                            {"By " + data.author}
                                                        </Accordion.Body>
                                                    </Accordion.Item>
                                                </Accordion>
                                                <Button variant="danger"  size="lg"
                                                        onClick={ ()=>{
                                                    setIsbnToDelete(data.isbn);
                                                    deleteBook();
                                                        }}>
                                                    Delete Book
                                                </Button>
                                            </Card.Body>
                                        </Card>
                                    </Col>

                                )
                            }
                        )
                    }
                </Row>
            </div>
        </div>
    );
}


function CardLoading() {
    let arr = [];
    for(let x = 0; x < 4; x++){
        arr.push(<Card style={{width: '18rem'}}>
            <Card.Img variant="top"  src="" style={{backgroundColor: "#8D5A16" }} width={286} height={360} />
            <Card.Body>
                <Accordion>
                    <Accordion.Item eventKey="0">
                        <Placeholder as={Accordion.Header} animation="glow">
                            <Placeholder xs={6}/>
                        </Placeholder>
                        <Placeholder as={Accordion.Body} animation="glow">
                            <Placeholder xs={7}/> <Placeholder xs={4}/> <Placeholder xs={4}/>{' '}
                            <Placeholder xs={6}/> <Placeholder xs={8}/>
                        </Placeholder>
                    </Accordion.Item>
                </Accordion>
            </Card.Body>
        </Card>)
    }
    return (
        <>
            {arr.values()}
        </>
    )
}

export default BookShelf;
