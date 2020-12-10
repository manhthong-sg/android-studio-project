const mongoose = require('mongoose');

const userSchema = new mongoose.Schema({
    displayName: {
        type: String,
        unique: true,
        validate: {
            validator: displayName => displayName !== "",
            message: "Display name is not null and empty"
        }
    },
    accountName: {
        type: String,
        unique: true,
        validate: {
            validator: accountName => accountName !== "",
            message: "Account name is not null and empty"
        }
    },
    password: {
        type: String,
        validate: {
            validator: pw => pw !== "",
            message: "Password is not null and empty"
        }
    },
    phoneNumber: {
        type: String, 
        validate: {
            validator: phoneNumber => /^[\d]{10}$/.test(phoneNumber),
            message: "Phone number is not valid"
        }
    }
}, { versionKey: false });

const User = new mongoose.model('user', userSchema, 'user');

const Login = async (accountName, password) => {
    try {
        const user = await User.findOne({ accountName: accountName, password: password});
        if(user == null) {
            return {};
        } 
        return user;
    } catch {
        console.log(err);
        return {};
    }
}

const GetUserById = async userId => {
    try {
        const user = await User.findById(userId);
        return user;
    } catch(err) {
        console.log(err)
        return null;
    }
}

const SearchUserByDisplayName = async displayName => {
    try {
        const regex = new RegExp(`^${ displayName }`);
        const users = await User.find({ displayName: { $regex: regex } });
        return users;
    } catch(err) {
        console.log(err);
        return null;
    }
}

const GetUserDisplayName = async userId => {
    try {
        const user = await User.findById(userId);
        if(user != null) {
            return user.displayName;
        }
        return null;
    } catch(err) {
        console.log(err);
        return null;
    }
}

const InsertUser = async (accountName, password, displayName, phoneNumber) => {
    var user = new User();
    user.accountName = accountName;
    user.password = password;
    user.displayName = displayName;
    user.phoneNumber = phoneNumber;
    try {
        await User.create(user);
    } catch(err) {
        console.log(err.toString());
        return err.toString();
    }
    return user;
}

const FindUserByAccountName = async accountName => {
    try {
        const users = await User.find({ accountName: accountName });
        return users;
    } catch(err) {
        console.log(err);
        return null;
    }
}

const UpdateUser = async (userId, accountName, displayName, password, phoneNumber) => {
    const updateObj = {
        accountName: accountName,
        displayName: displayName,
        password: password,
        phoneNumber: phoneNumber
    };
    try {
        const user = await User.findByIdAndUpdate(
            userId,
            updateObj,
            { new: true, runValidators: true }
        );
        return user;
    } catch(err) {
        console.log(err.toString());
        return err.toString();
    }
}

module.exports = {
    Login,
    GetUserById,
    SearchUserByDisplayName,
    GetUserDisplayName,
    InsertUser,
    FindUserByAccountName,
    UpdateUser
}