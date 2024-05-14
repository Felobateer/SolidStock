import { useState } from "react";
import { View, Text, TextInput, TouchableOpacity } from "react-native";
import React from "react";
import { Link } from "expo-router";

const register = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  return (
    <View className="flex-1 items-center justify-center bg-slate-600">
      <TextInput
        placeholder="Name"
        value={name}
        className="w-4/5 border border-slate-500 rounded-xl px-4 py-2 mb-4 text-lg"
      />
      <TextInput
        placeholder="Email"
        value={email}
        className="w-4/5 border border-slate-500 rounded-xl px-4 py-2 mb-4 text-lg"
      />
      <TextInput
        placeholder="Username"
        value={username}
        className="w-4/5 border border-slate-500 rounded-xl px-4 py-2 mb-4 text-lg"
      />
      <TextInput
        placeholder="Password"
        value={password}
        className="w-4/5 border border-slate-500 rounded-xl px-4 py-2 mb-4 text-lg"
      />
      <TouchableOpacity className="bg-yellow-600 px-4 py-2 w-fit mx-auto rounded-xl">
        <Text className="text-white font-semibold text-lg">Register</Text>
      </TouchableOpacity>
      <View className="flex flex-row">
        <Text className="text-white text-sm">
          Already have an account. then{" "}
        </Text>
        <Link href="/" className="text-yellow-600 text-sm">
          login now
        </Link>
      </View>
    </View>
  );
};

export default register;
