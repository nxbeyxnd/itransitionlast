S3handler = new S3handler(System.getenv("googleClientID"))

const aws = require('aws-sdk');

let s3 = new aws.S3({
    accessKeyId: process.env.googleClientId,
    secretAccessKey: process.env.googleClientSecret
});