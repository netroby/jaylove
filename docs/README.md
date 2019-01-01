# Jaylove

## Set up aws upload

add config to application-dev.yml

```yaml
       
jaylove:
  site:
    name: Love
    description: "Everything love"
    cdnUrl: "https://cdn.netroby.com/"

aws:
  access_key_id: "xxxxxx"
  secret_access_key:  "xxx"
  region: "us-east-1"
  bucket: "cdn.netroby.com"

```
