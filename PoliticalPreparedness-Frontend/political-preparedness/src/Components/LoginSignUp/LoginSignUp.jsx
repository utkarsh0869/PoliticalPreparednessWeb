import "./LoginSignUp.css";
import user_icon from "../Assets/person.png";
import email_icon from "../Assets/email.png";
import password_icon from "../Assets/password.png";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser, registerUser } from "../../Api/api";

export default function LoginSignUp() {
  const [action, setAction] = useState("Sign Up");
  const [userLoginData, setUserLoginData] = useState({
    email: "",
    password: "",
  });
  const [userRegisterData, setUserRegisterData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
  });
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleChange = (event) => {
    const { name, value } = event.target;
    if (action === "Login") {
      setUserLoginData((prevData) => ({ ...prevData, [name]: value }));
    } else {
      setUserRegisterData((prevData) => ({ ...prevData, [name]: value }));
    }
  };

  const handleLogin = async () => {
    setErrorMessage("");

    if (!userLoginData.email || !userLoginData.password) {
      setErrorMessage("Please enter your email and password.");
      return;
    }

    try {
      const result = await loginUser(userLoginData);
      console.log(result);
      navigate("/homepage");
    } catch (error) {
      setErrorMessage("Login failed. Please check your credentials.");
    }
  };

  const handleSignup = async () => {
    setErrorMessage("");
    if (
      !userRegisterData.firstName ||
      !userRegisterData.lastName ||
      !userRegisterData.email ||
      !userRegisterData.password
    ) {
      setErrorMessage("Please fill out all fields.");
      return;
    }

    try {
      const result = await registerUser(userRegisterData);
      console.log(result);
      setAction("Login");
      setErrorMessage("Registration successful. Please log in.");
    } catch (error) {
      setErrorMessage("Signup failed: " + error.message);
    }
  };

  return (
    <form
      onSubmit={(e) => {
        e.preventDefault();
        action === "Login" ? handleLogin() : handleSignup();
      }}
    >
      <div className="container">
        <div className="header">
          <div className="text">{action}</div>
          <div className="underline"></div>
        </div>

        {errorMessage && <div className="error-message">{errorMessage}</div>}

        <div className="inputs">
          {action === "Sign Up" && (
            <>
              <div className="input">
                <img src={user_icon} alt="user icon" />
                <input
                  type="text"
                  name="firstName"
                  placeholder="First name"
                  value={userRegisterData.firstName}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="input">
                <img src={user_icon} alt="user icon" />
                <input
                  type="text"
                  name="lastName"
                  placeholder="Last name"
                  value={userRegisterData.lastName}
                  onChange={handleChange}
                  required
                />
              </div>
            </>
          )}

          <div className="input">
            <img src={email_icon} alt="email icon" />
            <input
              type="email"
              name="email"
              placeholder="Email"
              value={
                action === "Login"
                  ? userLoginData.email
                  : userRegisterData.email
              }
              onChange={handleChange}
              required
            />
          </div>
          <div className="input">
            <img src={password_icon} alt="password icon" />
            <input
              type="password"
              name="password"
              placeholder="Password"
              value={
                action === "Login"
                  ? userLoginData.password
                  : userRegisterData.password
              }
              onChange={handleChange}
              required
            />
          </div>
        </div>

        {action === "Login" && (
          <div className="forgot-password">
            Forgot Password? <span>Click Here!</span>
          </div>
        )}

        <div className="submit-container">
          <button type="submit" className="submit">
            {action === "Login" ? "Login" : "Sign Up"}
          </button>
        </div>

        <div className="toggle-action">
          {action === "Login" ? (
            <span
              onClick={() => {
                setAction("Sign Up");
                setErrorMessage("");
              }}
            >
              Don’t have an account? <span className="highlight">Sign Up!</span>
            </span>
          ) : (
            <span
              onClick={() => {
                setAction("Login");
                setErrorMessage("");
              }}
            >
              Already have an account? <span className="highlight">Login!</span>
            </span>
          )}
        </div>
      </div>
    </form>
  );
}
