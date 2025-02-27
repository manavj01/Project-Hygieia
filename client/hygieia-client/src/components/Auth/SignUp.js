// import { useState } from "react";

// export default function SignUp() {
//   const [formData, setFormData] = useState({ fullName: "", email: "", password: "" });
//   const [message, setMessage] = useState("");

//   const handleChange = (e) => {
//     setFormData({ ...formData, [e.target.name]: e.target.value });
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     setMessage("");
    
//     try {
//       const response = await fetch("http://localhost:8080/api/users/register", {
//         method: "POST",
//         headers: { "Content-Type": "application/json" },
//         body: JSON.stringify(formData),
//       });

//       if (response.ok) {
//         setMessage("User registered successfully!");
//         setFormData({ fullName: "", email: "", password: "" });
//       } else {
//         const errorData = await response.json();
//         setMessage(errorData.message || "Failed to register");
//       }
//     } catch (error) {
//       setMessage("Error connecting to server");
//     }
//   };

//   return (
//     <div className="flex justify-center items-center h-screen bg-gray-100">
//       <form onSubmit={handleSubmit} className="bg-white p-6 rounded-lg shadow-md w-96">
//         <h2 className="text-2xl font-semibold mb-4">Sign Up</h2>
//         {message && <p className="text-red-500 mb-2">{message}</p>}
//         <input
//           type="text"
//           name="fullName"
//           placeholder="Full Name"
//           value={formData.fullName}
//           onChange={handleChange}
//           className="w-full p-2 border rounded mb-2"
//           required
//         />
//         <input
//           type="email"
//           name="email"
//           placeholder="Email"
//           value={formData.email}
//           onChange={handleChange}
//           className="w-full p-2 border rounded mb-2"
//           required
//         />
//         <input
//           type="password"
//           name="password"
//           placeholder="Password"
//           value={formData.password}
//           onChange={handleChange}
//           className="w-full p-2 border rounded mb-4"
//           required
//         />
//         <button type="submit" className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600">
//           Sign Up
//         </button>
//       </form>
//     </div>
    
//   );
// }







// import { useState } from "react";

// export default function SignUp() {
//   const [formData, setFormData] = useState({ fullName: "", email: "", password: "", confirmPassword: "" }); // Add confirm password
//   const [message, setMessage] = useState("");

//   const handleChange = (e) => {
//     setFormData({ ...formData, [e.target.name]: e.target.value });
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     setMessage("");

//     if (formData.password !== formData.confirmPassword) { // Password match check
//       setMessage("Passwords do not match!");
//       return;
//     }

//     try {
//       const response = await fetch("http://localhost:8080/api/users/register", {
//         method: "POST",
//         headers: { "Content-Type": "application/json" },
//         body: JSON.stringify(formData),
//       });

//       if (response.ok) {
//         setMessage("User registered successfully!");
//         setFormData({ fullName: "", email: "", password: "", confirmPassword: "" }); // Clear confirm password
//       } else {
//         const errorData = await response.json();
//         setMessage(errorData.message || "Failed to register");
//       }
//     } catch (error) {
//       setMessage("Error connecting to server");
//     }
//   };

//   return (
//     <div className="flex justify-center items-center h-screen bg-gray-100">
//       <div className="bg-white p-8 rounded-lg shadow-md w-96"> {/* Added wrapping div for better spacing */}
//         <h2 className="text-2xl font-semibold mb-4 text-center">Sign Up</h2> {/* Centered title */}
//         {message && <p className="text-red-500 mb-2">{message}</p>}
//         <input
//           type="text"
//           name="fullName"
//           placeholder="Full Name"
//           value={formData.fullName}
//           onChange={handleChange}
//           className="w-full p-3 border rounded mb-3 focus:outline-none focus:ring focus:border-blue-300" // Improved styling
//           required
//         />
//         <input
//           type="email"
//           name="email"
//           placeholder="Email"
//           value={formData.email}
//           onChange={handleChange}
//           className="w-full p-3 border rounded mb-3 focus:outline-none focus:ring focus:border-blue-300" // Improved styling
//           required
//         />
//         <input
//           type="password"
//           name="password"
//           placeholder="Create Password" // Changed placeholder
//           value={formData.password}
//           onChange={handleChange}
//           className="w-full p-3 border rounded mb-3 focus:outline-none focus:ring focus:border-blue-300" // Improved styling
//           required
//         />
//         <input
//           type="password"
//           name="confirmPassword"
//           placeholder="Confirm Password" // Confirm password field
//           value={formData.confirmPassword}
//           onChange={handleChange}
//           className="w-full p-3 border rounded mb-4 focus:outline-none focus:ring focus:border-blue-300" // Improved styling
//           required
//         />


//         <button
//           type="submit"
//           className="w-full bg-blue-500 text-white p-3 rounded hover:bg-blue-600 focus:outline-none focus:ring focus:ring-blue-300" // Improved button styling
//         >
//           Sign Up
//         </button>

//         <p className="mt-6 text-center">
//           Already have an account?{" "}
//           <a href="/login" className="text-blue-500 hover:underline">
//             Login
//           </a>
//         </p>
//       </div>
//     </div>
//   );
// }


// import { useState } from "react";
// import "./SignUp.css"; // Import the CSS file

// export default function SignUp() {
//   const [formData, setFormData] = useState({ fullName: "", email: "", password: "", confirmPassword: "" });
//   const [message, setMessage] = useState("");

//   const handleChange = (e) => {
//     setFormData({ ...formData, [e.target.name]: e.target.value });
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     setMessage("");

//     if (formData.password !== formData.confirmPassword) {
//       setMessage("Passwords do not match!");
//       return;
//     }

//     try {
//       const response = await fetch("http://localhost:8080/api/users/register", {
//         method: "POST",
//         headers: { "Content-Type": "application/json" },
//         body: JSON.stringify(formData),
//       });

//       if (response.ok) {
//         setMessage("User registered successfully!");
//         setFormData({ fullName: "", email: "", password: "", confirmPassword: "" });
//       } else {
//         const errorData = await response.json();
//         setMessage(errorData.message || "Failed to register");
//       }
//     } catch (error) {
//       setMessage("Error connecting to server");
//     }
//   };

//   return (
//     <div className="signup-container">
//       <div className="signup-box">
//         <h2 className="signup-title">Sign Up</h2>
//         {message && <p className="error-message">{message}</p>}
//         <form onSubmit={handleSubmit}>
//           <input type="text" name="fullName" placeholder="Full Name" value={formData.fullName} onChange={handleChange} required />
//           <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleChange} required />
//           <input type="password" name="password" placeholder="Create Password" value={formData.password} onChange={handleChange} required />
//           <input type="password" name="confirmPassword" placeholder="Confirm Password" value={formData.confirmPassword} onChange={handleChange} required />
//           <button type="submit">Sign Up</button>
//         </form>
//         <p className="login-link">
//           Already have an account? <a href="/login">Login</a>
//         </p>
//       </div>
//     </div>
//   );
// }





import { useState } from "react";

export default function SignUp() {
  const [formData, setFormData] = useState({
    fullName: "",
    email: "",
    password: "",
    confirmPassword: "",
  });
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");

    if (formData.password !== formData.confirmPassword) {
      setMessage("Passwords do not match!");
      return;
    }

    try {
      const response = await fetch("http://localhost:8080/api/users/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        setMessage("User registered successfully!");
        setFormData({ fullName: "", email: "", password: "", confirmPassword: "" });
      } else {
        const errorData = await response.json();
        setMessage(errorData.message || "Failed to register");
      }
    } catch (error) {
      setMessage("Error connecting to server");
    }
  };

  return (
    <div className="flex justify-center items-center min-h-screen bg-gradient-to-br from-green-100 to-blue-100">
      <div className="bg-white p-8 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-3xl font-semibold text-green-700 text-center mb-4">
          Welcome to Hygieia
        </h2>
        <p className="text-gray-500 text-center mb-6">
          Create your account and join our health-conscious community.
        </p>

        {message && <p className="text-red-500 text-center mb-4">{message}</p>}

        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            name="fullName"
            placeholder="Full Name"
            value={formData.fullName}
            onChange={handleChange}
            className="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-400"
            required
          />
          <input
            type="email"
            name="email"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
            className="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-400"
            required
          />
          <input
            type="password"
            name="password"
            placeholder="Create Password"
            value={formData.password}
            onChange={handleChange}
            className="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-400"
            required
          />
          <input
            type="password"
            name="confirmPassword"
            placeholder="Confirm Password"
            value={formData.confirmPassword}
            onChange={handleChange}
            className="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-400"
            required
          />

          <button
            type="submit"
            className="w-full bg-green-500 text-white p-3 rounded-lg text-lg font-semibold hover:bg-green-600 transition"
          >
            Sign Up
          </button>
        </form>

        <p className="mt-6 text-center text-gray-600">
          Already have an account?{" "}
          <a href="/login" className="text-green-600 font-medium hover:underline">
            Login
          </a>
        </p>
      </div>
    </div>
  );
}
