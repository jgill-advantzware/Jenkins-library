#!/usr/bin/groovy

package com.kajeet.jenkins

def firstNotify(result, message) {
        def webhookUrl = 'https://outlook.office.com/webhook/xxxxxxxx'
        def color = '#0000FF' // blue

        switch(result) {
            case 'SUCCESS':
                color = '#00FF00' // lime
                break
            case 'FAILURE':
                color = '#FF0000' // red
                break
            case 'ROLLBACK':
                color = '#000000' // black
                break
        }
        println "result " + result
        o365Msg = env.JOB_NAME + " Build " + env.BUILD_NUMBER + " **" + result + "**\n\n"
        o365Msg = o365Msg + "• [Console Output](${env.BUILD_URL}console)\n\n"
        o365Msg = o365Msg + "• Status: **" + message + "**"
        notify_office365(color, o365Msg, result, webhookUrl)
}

def notify_office365(color, message, status, webhookUrl) {
        office365ConnectorSend color: color, message: message, status: status, webhookUrl: webhookUrl
}
