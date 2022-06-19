#!/usr/bin/python
import requests
import time
import sys
id = sys.argv[1]
form_data = open('./userFiles/' + id + '_formData.txt').read()
Loginkey = open('./userFiles/' + id + '_loginKey.txt').read()
Sendkey = open('./userFiles/' + id + '_sendKey.txt').read()

url = 'https://pingan.ouc.edu.cn/ncov/wap/default/save'

Cookies = {'eai-sess': Loginkey}

headers = {
	'Accept':'application/json, text/javascript, */*; q=0.01',
	'Referer':'https://pingan.ouc.edu.cn/ncov/wap/default/index',
	'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36',
	'X-Requested-With':'XMLHttpRequest',
	'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
	}
requests.packages.urllib3.disable_warnings()
r = requests.post(url,data = form_data,headers = headers,cookies = Cookies,verify=False)


fturl = 'https://sctapi.ftqq.com/' + Sendkey + '.send?title= '+time.strftime("%Y-%m-%d %X", time.localtime())+'&desp='+ r.text

requests.get(fturl, verify=False)
