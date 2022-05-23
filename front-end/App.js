import {useState} from 'react';
import axios from "axios";
import './App.css';

export default function App() {

  const [result, setResult] = useState(0.0);
  const [error, setError] = useState("");
  const URL = "http://localhost:8080/";


  async function calculate() {
    let equation = document.getElementById("equation").value;
    console.log(equation);
    const CALC = `derive/?equation=${equation}`;
    console.log(CALC);
    console.log(URL + CALC);
    let value = await axios.get(URL + CALC);
    console.log(value);
    console.log(value.data);
    setResult(value.data);
    setError("");
  }

  return (
    <div id="container">
      <p>Derivative Calculator</p>
      <label htmlFor="equation"> Put equation</label>
      <input
        id="equation"
        type="string"
        name="input"
      />
      <div id="operations">
        <button onClick={calculate}>caculate</button>
      </div>
      <p>Result: {result}</p>
      <p>{error}</p>
    </div>
  );
}
