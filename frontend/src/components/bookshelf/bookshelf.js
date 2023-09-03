import Card from 'react-bootstrap/Card';
import axios from "axios";
import "../../App.scss";
import React, {useEffect, useState} from "react";
import {Accordion, Col, Row} from "react-bootstrap";
import {EndNav} from "../endNav";


function BasicExample() {
    const [listData,setListData]=useState([])
    useEffect(
        ()=>{
            axios.get("/booklist")
                .then(res =>
                {
                    console.log(res.data);
                    setListData(res.data);
                })
                .catch(error => console.log(error));
        },[]
    )
    return (
        <div id="topBar">
            <div className="px-5 pt-2 pb-2 d-flex flex-row" styles={{gap: 10 + 'px'}} id="nav">
                <div className="col" id="webName">
                    <h1>Booktopia</h1>
                </div>
                <div>
                    <h1>Book Shelf</h1>
                </div>
                <div className="ps-5 col">
                    <EndNav/>
                </div>
            </div>

            <div className="container overflow-hidden text-center p-5" id="bookShelf">
              <Row className="g-1" id="Shelf">
                  {listData.map((data, id) =>
                  {
                      return (
                          <Col key={id}>
                              <Card style={{ width: '18rem' }}>
                                  <Card.Img variant="top" src={data.cover} />
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
                                  </Card.Body>
                              </Card>
                          </Col>
                      )} )}
              </Row>
          </div>
        </div>
    );
}

export default BasicExample;
